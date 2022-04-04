package ntnu.karolisw.project_backend.model;

import lombok.*;
import ntnu.karolisw.project_backend.model.user.TeacherUser;

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
    // When course is added to teacher, then teacher is also added to course
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "course_teacher",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private Set<Course> courses = new HashSet<>();

    // One-to-one relationship with TeacherUser
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_user_id", referencedColumnName = "id")
    private TeacherUser teacherUser;

    @Override
    public String toString() {
        return "Teacher{" +
                "courses=" + courses +
                ", teacherUser=" + teacherUser +
                '}';
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
}

