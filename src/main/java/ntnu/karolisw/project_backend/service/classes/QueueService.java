package ntnu.karolisw.project_backend.service.classes;

import ntnu.karolisw.project_backend.dto.in.StudentInQueueIn;
import ntnu.karolisw.project_backend.model.*;
import ntnu.karolisw.project_backend.repository.*;
import ntnu.karolisw.project_backend.service.interfaces.QueueServiceI;
import ntnu.karolisw.project_backend.enumFolder.Status;
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

        // List of students for the specified assessment type
        ArrayList<StudentInQueue> students = new ArrayList<>();

        // Find the appropriate students
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
    public ResponseEntity<Object> setStudentState(long studentId, long courseId, String status) {
        List<StudentInQueue> students = queueRepository.findAllStudentsInQueueByCourseId(courseId);
        for(StudentInQueue student : students) {
            if (student.getStudent().getId() == studentId) {

                // Check that the string actually contains what it should!
                if(status.equalsIgnoreCase("available")) {
                    student.setStatusInQueue(Status.AVAILABLE);
                    studentInQueueRepository.save(student);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                else if(status.equalsIgnoreCase("waiting")) {
                    student.setStatusInQueue(Status.WAITING);
                    studentInQueueRepository.save(student);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                else if (status.equalsIgnoreCase("taken")) {
                    student.setStatusInQueue(Status.TAKEN);
                    studentInQueueRepository.save(student);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                else {
                    // Something wrong with the given state --> reject
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        }
        // If we have not reached a student yet, the student id might have been wrong
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> getStudentState(long studentId) {

        // Find the student in queue object
        Optional<StudentInQueue> student = studentInQueueRepository.getStudentInQueueUsingStudentId(studentId);

        // If the student id was present in db (and connected to SIQ entity)
        if(student.isPresent()) {
            Status status = student.get().getStatusInQueue();
            return new ResponseEntity<>(status.toString(), HttpStatus.OK);
        }

        // If student was not present
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> approveStudent(long studentId, int assignmentNumber, long courseId) {
        // Try to find the student
        Optional<Student> student = studentRepository.findById(studentId);

        // if the student exists
        if (student.isPresent()) {

            // Get all the assignments that the student has
            Set<Assignment> studentsAssignments = studentRepository.getAssignmentsByStudentId(studentId);

            // For each assignment
            for(Assignment assignment : studentsAssignments) {

                // If correct assignment number
                if(assignment.getAssignmentNumber() == assignmentNumber) {

                    // Get the course id of the assignment
                    long courseIdOfAssignment = assignment.getGroupOfAssignment().getCourse().getCourseId();

                    // If correct course id
                    if(courseIdOfAssignment == courseId) {

                        // Approve the assignment and update!
                        if(!assignment.isApproved()) {
                            assignment.setApproved(true);
                            assignmentRepository.save(assignment);
                            return new ResponseEntity<>(HttpStatus.OK);
                        }
                    }
                }
            }
        }
        // Student did not exist in db
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        /*
        // if the student exists
        if (student.isPresent()) {

            // Get the group of assignments that contain the specific assignment
            GroupOfAssignment group = getGroupOfAssignmentByAssignmentNumber
                    (assignmentNumber,courseId);
            if(group != null) {
                // We have the group of Assignments, and have to check if the student has the same assignments
                Set<Assignment> assignments = studentRepository.getAssignmentsByStudentId(studentId);

                // Set the assignment with the correct assignmentNumber = approved
                for (Assignment assignment : assignments) {
                    if(assignment.getAssignmentNumber() == assignmentNumber) {
                        assignment.setApproved(true);
                    }
                }
                // If we get here, the student did not have an assignment with that number
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // If we get here, the group was null
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        */
        // If we get here, the student did not exist

        return null;
    }


    // get group of assignment that assignment is part of
    @Override
    public ResponseEntity<Object> getGroupOfAssignment(long assignmentId) {
        return new ResponseEntity<>
                (assignmentRepository.getGroupOfAssignment(assignmentId),
                        HttpStatus.OK);
    }
    // get group of assignment when you have the number of the group and the course id
    // groupNumber = orderNr
    @Override
    public GroupOfAssignment getGroupOfAssignment(int groupNumber, long courseId){

        List<GroupOfAssignment> groups = courseRepository.getAllGroupsOfAssignmentByCourseId(courseId);
        for(GroupOfAssignment groupOfAssignment : groups) {
            if(groupOfAssignment.getOrderNr() == groupNumber) {
                return groupOfAssignment;
            }
        }
        // If there was no group of assignment with an assignment with that assignment - number
        return null;
    }


    // get group of assignment when you have the number of the group and the course id
    // groupNumber = orderNr
    @Override
    public GroupOfAssignment getGroupOfAssignmentByAssignmentNumber(int assignmentNumber, long courseId){

        List<GroupOfAssignment> groups = courseRepository.getAllGroupsOfAssignmentByCourseId(courseId);
        for(GroupOfAssignment group : groups) {
            // Go through the assignment
            for(Assignment assignment: group.getAssignments()) {
                if(assignment.getAssignmentNumber() == assignmentNumber) {
                    return group;
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
        List<Assignment> allAssignments = new ArrayList<>();

        // get all the groups
        List<GroupOfAssignment> groups = courseRepository.getAllGroupsOfAssignmentByCourseId(courseId);

        // For each group, get the assignments
        for(GroupOfAssignment group : groups) {
            allAssignments.addAll(group.getAssignments());
            // allAssignments.addAll(groupOfAssignmentRepository.getAllAssignmentsByGroupId(group.getGroupId()));
        }
        // return all assignments!
        return new ResponseEntity<>(allAssignments, HttpStatus.OK);
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

        // If the queue exists, we set it for the new siq entity
        if(queue.isPresent()){
            newSIQ.setQueue(queue.get());
            newSIQ.setAssessmentHelp(dto.isAssessmentHelp());
        }
        else {
            throw new IllegalStateException("There was no queue connected to the course with id: "
                    + dto.getCourseId());
        }

        // This student will be placed last in queue
        int previousPlacement = queue.get().getNumberOfWaitingStudents();
        newSIQ.setPlacementInQueue(previousPlacement + 1);

        /**
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
         */

        // Automatically available upon creation
        newSIQ.setStatusInQueue(Status.AVAILABLE);

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

        // Set the new number of waiting students (increment by one)
        queue.get().setNumberOfWaitingStudents(previousPlacement + 1);

        // Update
        queueRepository.save(queue.get());

        // Save the new SIQ object (persist cascade)
        studentInQueueRepository.save(newSIQ);

        // Entity successfully created
        return new ResponseEntity<>(newSIQ, HttpStatus.OK);
    }

    /**
     * @param dto this time should hold the studentInQueue entity's id
     * @return http-status = OK --> if deletion was successful
     */
    @Override
    public ResponseEntity<Object> deleteStudentInQueueEntity(StudentInQueueIn dto) {
        // Dto contains studentId

        Optional<StudentInQueue> siq = studentInQueueRepository.getStudentInQueueUsingStudentId(dto.getStudentId());

        // If the student in queue entity is present in db
        if(siq.isPresent()) {

            // SIQ holds a foreign key to student and queue --> set to null
            siq.get().setQueue(null);
            siq.get().setStudent(null);

            // With foreign keys == null, the SIQ entity can be deleted
            studentInQueueRepository.deleteById(dto.getStudentInQueueId());

            // If everything went well
            return new ResponseEntity<>(HttpStatus.OK);
        }
       else{
           throw new IllegalStateException("There was no SIQ object with the id: " + dto.getStudentInQueueId());
        }
    }

    @Override
    public ResponseEntity<Object> setQueueActive(StudentInQueueIn dto) {
        Optional<Queue> queue = queueRepository.getQueueByCourseId(dto.getCourseId());
        if(queue.isPresent()) {
            queue.get().setActive(dto.isActive());
            queueRepository.save(queue.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> isQueueActive(long courseId) {
        Optional<Queue> queue = queueRepository.getQueueByCourseId(courseId);
        if(queue.isPresent()) {
            boolean active = queue.get().isActive();
            return new ResponseEntity<>(active,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
