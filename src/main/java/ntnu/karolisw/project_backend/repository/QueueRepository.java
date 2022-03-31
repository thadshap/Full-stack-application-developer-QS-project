package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface QueueRepository extends JpaRepository<Queue, Long> {

    // get number of waiting students
    int findByQueueId(long queueId);

    // get queue
    Queue findQueueByQueueId(long queueId);

    // get all active queues
    List<Queue> findByActiveTrue();

    // get all inactive queues
    List<Queue> findByActiveFalse();

    // find the queue belonging to the specified course
    List<Queue> findByCourse(Course course);

    // get queue id by course id // todo "long queueId" could be more appropriate to return
    @Query("SELECT q.queueId FROM Queue q WHERE q.course.courseId = :id")
    Queue getQueueByCourseId(@Param("id") long courseId);


    // get all StudentInQueue objects where course id = x
    @Query("SELECT q.studentInQueues FROM Queue q WHERE q.course.courseId = :id")
    List<StudentInQueue> findAllStudentsInQueueByCourseId(@Param("id") long courseId);
}
