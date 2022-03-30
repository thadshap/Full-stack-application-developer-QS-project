package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Course, Long> {
    // get assignment by assignmentNumber
    // get all assignments with assignmentNumber
    // add assignment
    // add assignment to group of assignment
}
