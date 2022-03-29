package ntnu.karolisw.project_backend.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "queues")
/**
 * This table has a many-to-one relationship with course
 */
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "queue_id", nullable = false)
    private long queueId;

    @Column(name = "number_of_waiting_students", nullable = false)
    private int numberOfWaitingStudents;

    @Column(name = "active", nullable = false)
    private boolean active;

    //  Many-to-one relationship with course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
