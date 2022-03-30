package ntnu.karolisw.project_backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group_of_assignments")
/**
 * This table has a one-to-many connection with assignments
 * This table has a many-to-one relationship with course
 *
 */
public class GroupOfAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id", nullable = false)
    private Long groupId;

    // Ex: If number of assignment == 3, then order_nr can be 1, 2, or 3
    @Column(name = "order_nr", nullable = false)
    private int orderNr;

    @Column(name = "number_of_assignment", nullable = false)
    private int numberOfAssignment;

    @Column(name = "approved_assignments", nullable = false)
    private int approvedAssignments;

    // This table has a many-to-one relationship with course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // One-to-many connection with assignments
    @OneToMany(mappedBy = "groupOfAssignment")
    private Set<Assignment> assignments = new HashSet<>();
}
