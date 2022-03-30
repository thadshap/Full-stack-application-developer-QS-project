package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Assignment;
import ntnu.karolisw.project_backend.model.Course;
import ntnu.karolisw.project_backend.model.GroupOfAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    // get assignment by assignmentNumber
    List<Assignment> findByAssignmentNumber(int assignmentNumber);

    // get all approved assignments
    List<Assignment> findByApprovedTrue(long groupId);

}
