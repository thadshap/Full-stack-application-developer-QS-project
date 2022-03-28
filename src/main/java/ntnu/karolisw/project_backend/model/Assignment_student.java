package ntnu.karolisw.project_backend.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "assignment_student") //todo check out which tables should be nullable
public class Assignment_student {

    @Id // TODO SHOULD THIS BE HERE ?
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // TODO FOREIGN KEY
    @Column(name = "assignment_id", nullable = false)
    private long assignment_id; // todo should these be long? check the last project for foreign keys

    // TODO foreign key
    @Column(name = "student_nr", nullable = false)
    private long student_nr;

    @Column(name = "status_approved", nullable = false)
    private boolean status_approved;
}
