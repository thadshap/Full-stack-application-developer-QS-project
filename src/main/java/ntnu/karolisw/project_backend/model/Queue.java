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

    // One-to-one relationship with course
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    // One-to-many relationship with StudentInQueue
    @OneToMany(mappedBy = "queue")
    private Set<StudentInQueue> studentInQueues = new HashSet<>();
}
