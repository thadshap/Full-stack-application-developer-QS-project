package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.GroupOfAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupOfAssignmentRepository extends JpaRepository<GroupOfAssignment, Long> {
    // get all where order_nr
    // update order_nr
    // update number of assignment --> must also update order_nr for all the other objects
    // update number of approved assignments
    // get all groups of assignment where course id =


}
