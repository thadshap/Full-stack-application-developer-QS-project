package ntnu.karolisw.project_backend.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_in_queue")
/**
 * This table has a many-to-one connection with student
 */
public class StudentInQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_in_queue_id", nullable = false)
    private Long studentInQueueId;

    @Column(name = "placement_in_queue", nullable = false)
    private int placementInQueue;

    @Column(name = "digital", nullable = false)
    private boolean digital;

    // These attributes are nullable if digital == true
    @Column(name = "campus")
    private String campus;

    @Column(name = "building")
    private String building;

    @Column(name = "room")
    private String room;

    @Column(name = "tableNumber")
    private int tableNumber;

    // Help or approved
    @Column(name = "assessmentHelp", nullable = false)
    private boolean assessmentHelp;

    // Can be available, taken, waiting (when you have to close the machine and not be taken out of the queue)
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_in_queue", nullable = false)
    private Status statusInQueue;

    // One-to-one connection with student
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Student student;

    // Many-to-one connection with queue
    @ManyToOne
    @JoinColumn(name = "queue_id")
    private Queue queue;
}

