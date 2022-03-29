package ntnu.karolisw.project_backend.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "student_in_queue")
/**
 * This table has a many-to-one connection with student
 */
public class StudentInQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "queue_id", nullable = false)
    private long queue_id;

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

    @Column(name = "table")
    private int table;

    // Help or approved
    @Column(name = "type", nullable = false)
    private boolean type;

    // Can be available, taken, waiting (when you have to close the machine and not be taken out of the queue)
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_in_queue", nullable = false)
    private Status statusInQueue; // todo check all types

    // todo foreign key
    @Column(name = "student_id", nullable = false)
    private long studentId;

    // One-to-one connection with student
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;
}

