package ntnu.karolisw.project_backend.service;

import ntnu.karolisw.project_backend.model.*;
import ntnu.karolisw.project_backend.repository.*;
import ntnu.karolisw.project_backend.repository.userRepo.AdminUserRepository;
import ntnu.karolisw.project_backend.repository.userRepo.StudentUserRepository;
import ntnu.karolisw.project_backend.repository.userRepo.TeacherUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QueueService {

    @Autowired
    QueueRepository queueRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentInQueueRepository studentInQueueRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    GroupOfAssignmentRepository groupOfAssignmentRepository;


    // get all students that are in queue digitally
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
    public ResponseEntity<Object> setStudentState(long studentId, long courseId, Status status) {
        List<StudentInQueue> students = queueRepository.findAllStudentsInQueueByCourseId(courseId);
        for(StudentInQueue student : students) {
            if (student.getStudent().getId() == studentId) {
                student.setStatusInQueue(status);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
    public ResponseEntity<Object> getGroupOfAssignment(long assignmentId) {
        return new ResponseEntity<>
                (assignmentRepository.getGroupOfAssignment(assignmentId),
                        HttpStatus.OK);
    }

    // get group of assignment when you have the number of the assignment and the course id
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

}
