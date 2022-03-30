package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.StudentInQueue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentInQueueRepository extends JpaRepository<StudentInQueue, Long> {
    // get student with statusInQueue =
    // set statusInQueue
    // get student with placement in queue =
    // set placement in queue =
    // get all students in queue with queue id =
    // get all students in queue with queue id = x and digital = y
    // get all students with campus =
    // get all students with building =
    // get all students with room =
    // get all students with tableNumber =
    // upon remove student from queue --> remove student from this class as well --> delete?


}
