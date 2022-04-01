package ntnu.karolisw.project_backend.service.classes;

import ntnu.karolisw.project_backend.dto.in.StudentInQueueIn;
import ntnu.karolisw.project_backend.model.*;
import ntnu.karolisw.project_backend.repository.*;
import ntnu.karolisw.project_backend.service.interfaces.QueueServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QueueService implements QueueServiceI {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentInQueueRepository studentInQueueRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private GroupOfAssignmentRepository groupOfAssignmentRepository;


    // get all students that are in queue digitally
    @Override
    public ResponseEntity<Object> getAllStudentsInDigitalQueue(long courseId) {
        // get all students in queue for the specified course id
        List<StudentInQueue> studentsInQueue = queueRepository.findAllStudentsInQueueByCourseId(courseId);

        ArrayList<StudentInQueue> digital = new ArrayList<>();
        // return all students that are signed up to participate digitally
        for(StudentInQueue student : studentsInQueue) {
            if(student.isDigital()){
                digital.add(student);
            }
        }
        return new ResponseEntity<> (digital, HttpStatus.OK);
    }


    // get all students that are in queue digital and vice versa
    @Override
    public ResponseEntity<Object> getAllStudentsInQueueAtSchool(long courseId) {
        // get all students in queue for the specified course id
        List<StudentInQueue> studentsInQueue = queueRepository.findAllStudentsInQueueByCourseId(courseId);
        ArrayList<StudentInQueue> atSchool = new ArrayList<>();
        // return all students that are signed up to participate digitally
        for(StudentInQueue student : studentsInQueue) {
            if(!student.isDigital()){
                atSchool.add(student);
            }
        }
        return new ResponseEntity<> (atSchool, HttpStatus.OK);
    }

    // retrieve all students from queue that need approval (assessmentHelp for studentInQueue)

    /**
     * Retrieve all students from queue that need approval or help (assessmentHelp for studentInQueue)
     * @param courseId is the course we want to check the queue for
     * @param assessment is true if we want to retrieve students that need assessment;
     *                   is false if we want to retrieve students that need help
     * @return a list of the students that pertain to the assessment value within the specified course-id
     */
    @Override
    public ResponseEntity<Object> getStudentsForAssessmentOrHelp(long courseId, boolean assessment) {
        // get all students in queue for the specified course id
        List<StudentInQueue> studentsInQueue = queueRepository.findAllStudentsInQueueByCourseId(courseId);
        ArrayList<StudentInQueue> students = new ArrayList<>();
        for(StudentInQueue student : studentsInQueue) {
            if(student.isAssessmentHelp() == assessment){
                students.add(student);
            }
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


    /**
     * Set student as available, taken, waiting
     * @param studentId is the id of the student
     * @param courseId is the course id of the course the student is in queue for
     * @param status can be the:
     *               - AVAILABLE
     *               - TAKEN
     *               - WAITING
     *
     * @return the student in queue object with the altered state
     */
    @Override
    public ResponseEntity<Object> setStudentState(long studentId, long courseId, Status status) {
        List<StudentInQueue> students = queueRepository.findAllStudentsInQueueByCourseId(courseId);
        for(StudentInQueue student : students) {
            if (student.getStudent().getId() == studentId) {
                student.setStatusInQueue(status);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> approveStudent(long studentId, int assignmentNumber, long courseId) {
        // if the student exists and has an assignment with that id that is not approved
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            GroupOfAssignment group = getGroupOfAssignment(assignmentNumber,courseId);
            if(group != null) {
                // We have the group of Assignments, and have to check if the student has the same assignments
                Set<Assignment> assignments = studentRepository.getAssignmentsByStudentId(studentId);

                // Set the assignment with the correct assignmentNumber = approved
                for (Assignment assignment : assignments) {
                    if(assignment.getAssignmentNumber() == assignmentNumber) {
                        assignment.setApproved(true);

                        // increment the number of accepted assignments in the group of assignments
                        group.setApprovedAssignments( group.getApprovedAssignments() + 1 );
                        return new ResponseEntity<>(group, HttpStatus.OK);
                    }
                }
                // If we get here, the student did not have an assignment with that number
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // If we get here, the group was null
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // If we get here, the student did not exist
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // get group of assignment that assignment is part of
    @Override
    public ResponseEntity<Object> getGroupOfAssignment(long assignmentId) {
        return new ResponseEntity<>
                (assignmentRepository.getGroupOfAssignment(assignmentId),
                        HttpStatus.OK);
    }

    // get group of assignment when you have the number of the assignment and the course id
    @Override
    public GroupOfAssignment getGroupOfAssignment(int assignmentNumber, long courseId){

        List<GroupOfAssignment> groups = courseRepository.getAllGroupsOfAssignmentByCourseId(courseId);
        for(GroupOfAssignment groupOfAssignment : groups) {
            // Go through the assignment
            for(Assignment assignment: groupOfAssignment.getAssignments()) {
                if(assignment.getAssignmentNumber() == assignmentNumber) {
                    return groupOfAssignment;
                }
            }
        }
        // If there was no group of assignment with an assignment with that assignment - number
        return null;
    }

    // getAllStudentsInQueue
    @Override
    public ResponseEntity<Object> getAllStudentsInQueue(long courseId) {
        List<StudentInQueue> students = queueRepository.findAllStudentsInQueueByCourseId(courseId);
        if(students != null) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // get all assignments in specified course id and return them in order to display in frontend
    @Override
    public ResponseEntity<Object> getAllAssignmentsInCourse(long courseId) {
        List<Assignment> allAssignment = new ArrayList<>();
        // get all the groups
        List<GroupOfAssignment> groups = courseRepository.getAllGroupsOfAssignmentByCourseId(courseId);
        // For each group, get the assignments
        for(GroupOfAssignment groupOfAssignment : groups) {
            allAssignment.addAll(groupOfAssignmentRepository.getAllAssignmentsByGroupId(groupOfAssignment.getGroupId()));
        }
        // return all assignments!
        return new ResponseEntity<>(allAssignment, HttpStatus.OK);
    }

    /**
     * When a student wants to enter a queue, a StudentInQueue entity must be created.
     * This new entity is used in the queue - logic of the front-end and is therefore
     * important to both create/delete at the appropriate times:
     *            Created --> When the student enters the queue (this will make them visible in the queue)
     *            Deleted --> When the student's assignment is approved,
     *
     * @param dto is the data transfer object send when the user tries to enter the queue
     * @return the new StudentInQueue entity created in this method
     */
    @Override
    public ResponseEntity<Object> createStudentInQueueEntity(StudentInQueueIn dto) {
        StudentInQueue newSIQ = new StudentInQueue();

        // Get the queue
        Optional<Queue> queue = queueRepository.getQueueByCourseId(dto.getCourseId());
        // Add the general attributes
        if(queue.isPresent()){
            newSIQ.setQueue(queue.get());
            newSIQ.setAssessmentHelp(dto.isAssessmentHelp());
        }
        else {
            throw new IllegalStateException("There was no queue connected to the course with id: "
                    + dto.getCourseId());
        }

        // Get size of array of students in current queue + 1
        newSIQ.setPlacementInQueue(queueRepository.
                findAllStudentsInQueueByCourseId(dto.getCourseId()).
                size() + 1 );

        // 1 =  AVAILABLE; 2 = TAKEN; 3 = WAITING;
        int status = dto.getStatusInQueue();

        if(status == 1) {
            newSIQ.setStatusInQueue(Status.AVAILABLE);
        }
        else if(status == 2) {
            newSIQ.setStatusInQueue(Status.TAKEN);
        }
        else if(status == 3) {
            newSIQ.setStatusInQueue(Status.WAITING);
        }
        else {
            throw new IllegalArgumentException("The status was not numbers 1-3, but: " + status);
        }

        // If digital help/assessment requested
        if(dto.isDigital()) {
            newSIQ.setDigital(true);
        }

        // Else, set digital and add the school attributes (campus, building etc.)
        else {
            newSIQ.setDigital(false);
            newSIQ.setCampus(dto.getCampus());
            newSIQ.setBuilding(dto.getBuilding());
            newSIQ.setRoom(dto.getRoom());
            newSIQ.setTableNumber(dto.getTableNumber());
        }

        // Assign the foreign keys ( student and queue )
        Optional<Student> student = studentRepository.findById(dto.getStudentId());
        if(student.isPresent()) {
            newSIQ.setStudent(student.get());
            newSIQ.setQueue(queue.get());
        }
        // Save the new SIQ object
        studentInQueueRepository.save(newSIQ); // todo are the foreign keys present in the other two tables now?
        // If the method does not throw an exception until we get here, then the entity was successfully created
        return new ResponseEntity<>(newSIQ, HttpStatus.OK);
    }

    /**
     * todo cascade for the foreign keys involved hmm
     * @param dto this time should hold the studentInQueue entity's id
     * @return http-status = OK --> if deletion was successful
     */
    @Override
    public ResponseEntity<Object> deleteStudentInQueueEntity(StudentInQueueIn dto) {
        // get the SIQ id
        long id = dto.getStudentInQueueId();
        // If this SIQ object exists
        if(studentInQueueRepository.existsById(id)) {
            studentInQueueRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
       else{
           throw new IllegalStateException("There was no SIQ object with the id: " + id);
        }
    }
}
