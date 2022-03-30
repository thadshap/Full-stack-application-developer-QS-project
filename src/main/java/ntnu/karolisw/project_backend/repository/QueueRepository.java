package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Course;
import ntnu.karolisw.project_backend.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueueRepository extends JpaRepository<Queue, Long> {
    // get number of waiting students
    int findWaitingStudentsWhereQueueId(long queueId);
    int findWaitingStudentsWhereCourseId(long courseId);

    // get queue
    Queue findQueueByQueueId(long queueId);

    // get all active queues
    List<Queue> findByActiveTrue();

    // get all inactive queues
    List<Queue> findByActiveFalse();

    // find the queue belonging to the specified course
    List<Queue> findByCourse(Course course);
}
