package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Assignment;
import ntnu.karolisw.project_backend.model.Course;
import ntnu.karolisw.project_backend.model.GroupOfAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    // get assignment by assignmentNumber
    List<Assignment> findByAssignmentNumber(int assignmentNumber);

    // get all approved assignments
    List<Assignment> findByApprovedTrue(); //todo wrong?

    // get the group of assignment this assignment belongs to
    @Query("SELECT a.groupOfAssignment FROM Assignment a WHERE a.assignmentId= :id")
    List<Assignment> getGroupOfAssignment(@Param("id") long assignmentId);

    // get the group id of the group this assignment belongs to
    @Query("SELECT a.groupOfAssignment.groupId FROM Assignment a WHERE a.assignmentId= :id")
    long getGroupIdOfAssignment(@Param("id") long assignmentId);
}

