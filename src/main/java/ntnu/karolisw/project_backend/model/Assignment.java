package ntnu.karolisw.project_backend.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "assignments")

public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "assignment_number", nullable = false)
    private int assignment_number;

    // TODO foreign key
    @Column(name = "group_id", nullable = false)
    private long group_id;
}
