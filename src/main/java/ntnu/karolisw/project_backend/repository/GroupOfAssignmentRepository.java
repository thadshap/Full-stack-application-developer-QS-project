package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Assignment;
import ntnu.karolisw.project_backend.model.Course;
import ntnu.karolisw.project_backend.model.GroupOfAssignment;
import ntnu.karolisw.project_backend.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface GroupOfAssignmentRepository extends JpaRepository<GroupOfAssignment, Long> {

    // get all where order_nr and id
    List<GroupOfAssignment> findByGroupIdAndOrderNr(long groupId, int orderNr);

    // get all groups of assignment where courseId = id
    List<GroupOfAssignment> findByCourse(Course course);

    // find all assignments where groupId = id
    List<GroupOfAssignment> findByGroupId(long groupId);

    // get how many assignments are approved
    /**
    int findApprovedAssignmentsByGroupId(long groupId); //todo wrong...
    @Query("SELECT g.assignments FROM GroupOfAssignment g, Assignment a WHERE a.approved= :true")
    List<Assignment> getAllApprovedAssignmentsByGroupId(@Param("id") long groupId);
*/

    // get all the assignments in a group of assignments (by id)
    @Query("SELECT g.assignments FROM GroupOfAssignment g WHERE g.groupId = :id")
    List<Assignment> getAllAssignmentsByGroupId(@Param("id") long groupId);

}
