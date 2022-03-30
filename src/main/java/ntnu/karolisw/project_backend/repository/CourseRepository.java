package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Course;
import ntnu.karolisw.project_backend.model.Student;
import ntnu.karolisw.project_backend.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

// todo get or find?
public interface CourseRepository extends JpaRepository<Course, Long> {
    // get all from course where teacher id =
    List<Course> findByTeacherId(Long teacherId);

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
}
