package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // get course by course code
    Course findCourseByCourseCode(String courseCode);

    // get course by name
    Course findCourseByName(String courseName);

    // get all courses that have startDate = y
    List<Course> findByStartDate(Date startDate);

    // get all with start date before date = c
    List<Course> findByStartDateBefore(Date startDate);

    // get all with start date after date = c
    List<Course> findByStartDateAfter(Date startDate);

    // get all courses with a startDate between A and B
    List<Course> findByStartDateBetween(Date startDate, Date endDate);

    // get all teachers where course id = x
    @Query("SELECT c.teachers FROM Course c WHERE c.courseId = :id")
    Set<Teacher> getTeachersByCourseId(@Param("id") long id);

    // get all students where course id = y
    @Query("SELECT c.students FROM Course c WHERE c.courseId = :id")
    Set<Student> getStudentsByCourseId(@Param("id") long id);

    // get all studentAssistants where course id = z
    @Query("SELECT c.studentAssistants FROM Course c WHERE c.courseId = :id")
    Set<Student> getStudentAssistantsByCourseId(@Param("id") long id);

    // get all groups of assignment within a course
    @Query("SELECT c.groupsOfAssignments FROM Course c WHERE c.courseId = :id")
    List<GroupOfAssignment> getAllGroupsOfAssignmentByCourseId(@Param("id") long courseId);

    // todo get the queue --> one-to-one
    @Query("SELECT c.queue FROM Course c WHERE c.courseId = :id")
    Queue getQueueByCourseId(@Param("id") long courseId);
}
