package ntnu.karolisw.project_backend.model;

import lombok.*;
import ntnu.karolisw.project_backend.model.user.StudentUser;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")

/**
 * This table extends the Person superClass
 *
 * This table has a many-to-many connection with course
 * This table has a many-to-many connection with assignment (this table = parent entity)
 */
public class Student extends Person {

    // Many-to-many connection with assignment
    // Cascade only goes the other way
    @ManyToMany()
    @JoinTable(
            name = "assignment_student",
            joinColumns = { @JoinColumn(name = "id") },
            inverseJoinColumns = { @JoinColumn(name = "assignment_id") }
    )
    private Set<Assignment> assignments = new HashSet<>();

    // Many-to-many connection with courses
    // No cascade
    @ManyToMany()
    @JoinTable(
            name = "course_student",
            joinColumns = { @JoinColumn(name = "id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private Set<Course> courses = new HashSet<>();

    // Creating the student assistant table as a many-to-many connection with course
    // If a course is deleted, the student assistants are too (cascade.remove from course table)
    @ManyToMany()
    @JoinTable(
            name = "student_assistant",
            joinColumns = { @JoinColumn(name = "id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    // private Set<Student> studentAssistants;
    private Set<Course> taInCourses;

    // One-to-one relationship with student_in_queue
    // If student is removed, so is the student in queue entity
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private StudentInQueue studentInQueue;

    // One-to-one relationship with studentUser
    // If student is removed, so is the StudentUser entity
    @OneToOne(mappedBy = "student", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private StudentUser studentUser;

    public void addCourse(Course course) {
        courses.add(course);
    }
    public void addTaInCourse(Course course) {
        taInCourses.add(course);
    }
}
