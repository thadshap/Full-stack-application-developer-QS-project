package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Queue;
import ntnu.karolisw.project_backend.enumFolder.Status;
import ntnu.karolisw.project_backend.model.StudentInQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentInQueueRepository extends JpaRepository<StudentInQueue, Long> {
    // get student with statusInQueue = status
    List<StudentInQueue> findByStatusInQueue(Status status);

    // get student with placement in queue =
    StudentInQueue findStudentByPlacementInQueue(int placement);

    // get all students in queue with queue id =
    List<StudentInQueue> findByStudentIdAndQueue(long studentId, Queue queue);
    List<StudentInQueue> findByQueue(Queue queue);

    // get all students in queue with queue id = x and digital = y
    List<StudentInQueue> findByQueueAndDigitalTrue(Queue queue);
    List<StudentInQueue> findByQueueAndDigitalFalse(Queue queue);


    // get all students with campus =
    List<StudentInQueue> findByCampus(String campus);

    // get all students with building =
    List<StudentInQueue> findByBuilding(String building);

    // get all students with room =
    List<StudentInQueue> findByRoom(String room);

    // get all students with tableNumber =
    List<StudentInQueue> findByTableNumber(int tableNumber);

    // get all students with all the above
    List<StudentInQueue> findByCampusAndBuildingAndRoomAndTableNumber(String campus, String building,
                                                                     String room, int tableNumber);

    // get student in queue object using student id
    @Query("SELECT s FROM StudentInQueue s WHERE s.student.id = :id")
    Optional<StudentInQueue> getStudentInQueueUsingStudentId(@Param("id") long id);
}
