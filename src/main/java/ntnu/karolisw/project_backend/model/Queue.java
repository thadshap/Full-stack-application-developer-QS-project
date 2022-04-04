package ntnu.karolisw.project_backend.model;

import lombok.*;
import org.apache.tomcat.jni.Address;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "queues")
/**
 * This table has a one-to-one relationship with course
 */
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "queue_id", nullable = false)
    private Long queueId;

    @Column(name = "number_of_waiting_students", nullable = false)
    private int numberOfWaitingStudents;

    @Column(name = "active", nullable = false)
    private boolean active;

    // One-to-one relationship with course where queue is deleted if the course is deleted.
    // There is no cascade from this class to course, meaning that the deletion cascade only goes one way
    @OneToOne(fetch = FetchType.LAZY)
    /**
    @JoinTable(
            name = "queue_course",
            joinColumns = @JoinColumn(name = "queue_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    */
    @JoinColumn(name = "course_id")
    private Course course;

    // One-to-many relationship with StudentInQueue
    // If queue is deleted, then the student in queue objects will be deleted too
    @OneToMany(mappedBy = "queue", cascade = CascadeType.REMOVE)
    private Set<StudentInQueue> studentInQueues = new HashSet<>();
}
