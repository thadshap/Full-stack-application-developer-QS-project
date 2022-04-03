package ntnu.karolisw.project_backend.service.interfaces;

import ntnu.karolisw.project_backend.dto.in.StudentInQueueIn;
import ntnu.karolisw.project_backend.model.GroupOfAssignment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface QueueServiceI {
    // get all students that are in queue digitally
    ResponseEntity<Object> getAllStudentsInDigitalQueue(long courseId);

    // get all students that are in queue digital and vice versa
    ResponseEntity<Object> getAllStudentsInQueueAtSchool(long courseId);

    ResponseEntity<Object> getStudentsForAssessmentOrHelp(long courseId, boolean assessment);

    ResponseEntity<Object> setStudentState(long studentId, long courseId, String status);

    ResponseEntity<Object> approveStudent(long studentId, int assignmentNumber, long courseId);

    // get group of assignment that assignment is part of
    ResponseEntity<Object> getGroupOfAssignment(long assignmentId);

    // get group of assignment when you have the number of the assignment and the course id
    GroupOfAssignment getGroupOfAssignment(int assignmentNumber, long courseId);

    // get group of assignment when you have the number of the group and the course id
    // groupNumber = orderNr
    GroupOfAssignment getGroupOfAssignmentByAssignmentNumber(int assignmentNumber, long courseId);

    // getAllStudentsInQueue
    ResponseEntity<Object> getAllStudentsInQueue(long courseId);

    // get all assignments in specified course id and return them in order to display in frontend
    ResponseEntity<Object> getAllAssignmentsInCourse(long courseId);

    ResponseEntity<Object> createStudentInQueueEntity(StudentInQueueIn dto);

    ResponseEntity<Object> deleteStudentInQueueEntity(StudentInQueueIn dto);

    ResponseEntity<Object> setQueueActive(StudentInQueueIn dto);

    ResponseEntity<Object> isQueueActive(long courseId);

    ResponseEntity<Object> getStudentState(long studentId);
}
