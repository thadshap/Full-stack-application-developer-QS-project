package ntnu.karolisw.project_backend.service.interfaces;

import ntnu.karolisw.project_backend.dto.in.CourseIn;
import ntnu.karolisw.project_backend.dto.in.GroupIn;
import ntnu.karolisw.project_backend.model.GroupOfAssignment;
import ntnu.karolisw.project_backend.model.Student;
import ntnu.karolisw.project_backend.model.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface CourseServiceI {
    // Mark a specified course as archived
    ResponseEntity<Object> markAsArchived(long courseId);

    ResponseEntity<Object> createCourse(CourseIn newCourse);

    // get all students in a course
    ResponseEntity<Object> getAllStudentsInCourse(long courseId);

    // add a list of student assistant to a course
    ResponseEntity<Object> addStudentAssistant(long courseId, String studentAssistantEmail);

    // add a teacher to a course
    ResponseEntity<Object> addTeacher(long courseId, String teacherEmail);

    // add a student to a course
    ResponseEntity<Object> addStudent(long courseId, String studentEmail);

    // Deletes a student from a course
    ResponseEntity<Object> removeStudent(long courseId, long studentId);

    // update start date
    ResponseEntity<Object> updateStartDate(long courseId, Date startDate);

    // update end date
    ResponseEntity<Object> updateEndDate(long courseId, Date endDate);

    // update number of assignments
    ResponseEntity<Object> updateNumberOfAssignments(long courseId, int numberOfAssignments);

    // update min_approved_assignments
    ResponseEntity<Object> updateMinApprovedAssignments(long courseId, int numberOfApprovedAssignments);

    // update number_parts_assignments
    ResponseEntity<Object> updateNumberPartsAssignments(long courseId, int numberOfParts);

    // setArchived --> when course is archived --> the queue must be deleted!
    ResponseEntity<Object> archiveCourse(long courseId);

    // get number of approved assignments necessary for a certain course
    ResponseEntity<Object> getNumberOfApprovedAssignmentsByCourse(long courseId);

    // get all the assignments for a student with student id
    ResponseEntity<Object> getAllAssignmentsForStudent(long studentId);

    // getAssignmentsByStudentId
    ResponseEntity<Object> getAllAssignmentsForStudentInCourse(long studentId, long courseId);

    // get number of approved assignments for a student with student id
    int getAllApprovedAssignmentsForStudent(long studentId) throws Exception;

    // get all courses for student with student id (whole object)
    ResponseEntity<Object> getAllCoursesForStudent(long studentId);


    ResponseEntity<Object> getAllCourses();

    ResponseEntity<Object> getAllCoursesByTeacherId(long teacherId);

    List<GroupOfAssignment> getAllGroupsOfAssignmentByCourseId(long courseId);

    boolean addGroupOfAssignmentToCourse(GroupIn dto) throws Exception;

    ResponseEntity<Object> deleteCourse(long courseId);
}
