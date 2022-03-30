package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepository extends JpaRepository<Queue, Long> {
    // get number of waiting students
    // remove student from number of waiting students
    // add number of waiting students
    // set queue to active
    // remove
}
