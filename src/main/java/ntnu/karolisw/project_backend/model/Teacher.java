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
@Table(name = "teachers")
/**
 * This table extends the Person superClass
 *
 * This table has a many-to-many connection with course
 */
public class Teacher extends Person{

    // Many-to-many connection with course
    @ManyToMany(cascade = {CascadeType.ALL} )
    @JoinTable(
            name = "course_teacher",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private Set<Assignment> courses = new HashSet<>();
}

