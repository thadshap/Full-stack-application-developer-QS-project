package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // get all from course where teacher id =
    // get course by course code
    // get course by name
    // get all courses that have startDate =
    // get all courses with a startDate between A and B
    // update start data
    // update end date
    // update number of assignments
    // update min_approved_assignments
    // update number_parts_assignments
    // setArchived --> when course is archived --> the queue must be deleted!


}
