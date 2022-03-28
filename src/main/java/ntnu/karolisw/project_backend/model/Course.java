package ntnu.karolisw.project_backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "courses") //todo check out which tables should be nullable
// todo what should the generation type in id actually be ?
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long course_nr;

    @Column(name = "course_code", nullable = false)
    private String course_code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_data", nullable = false)
    private Date start_date;

    @Column(name = "number_of_assignments", nullable = false)
    private int number_of_assignments;

    @Column(name = "min_approved_assignments", nullable = false)
    private int min_approved_assignments;

    // todo this name could probably be better? original: ant_deler_øvinger
    @Column(name = "number_parts_assignments", nullable = false)
    private int number_parts_assignments;

    // todo foreign key + should it be int and administrator_id
    @Column(name = "administrator", nullable = false)
    private String administrator;
}
