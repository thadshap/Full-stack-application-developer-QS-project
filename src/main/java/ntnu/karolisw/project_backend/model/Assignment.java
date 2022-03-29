package ntnu.karolisw.project_backend.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "assignments")

/**
 * This table has a many-to-many relationship with student (this table = child entity)
 * This table has a many-to-one relationship with group_of_assignment
 */
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id", nullable = false)
    private long assignmentId;

    @Column(name = "assignment_number", nullable = false)
    private int assignmentNumber;

    // Many-to-many relationship with student --> middle table = assignment_student
    @ManyToMany(mappedBy = "assignments")
    private Set<Student> students = new HashSet<>();

    // Many-to-one relationship with group_of_assignment
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupOfAssignment groupOfAssignment;

    // Timestamp for usage in frontend
    @Temporal( TemporalType.TIMESTAMP )
    @CreationTimestamp
    @Column(name = "created_on")
    private Date createdOn;

}
