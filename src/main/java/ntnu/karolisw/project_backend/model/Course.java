package ntnu.karolisw.project_backend.model;

import lombok.*;

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
@Table(name = "courses")
/**
 * This table has a many-to-many relationship with teachers
 * This table has a many-to-many relationship with students
 * This table has a many-to-one relationship with administrator (teacher)
 * This table has a one-to-many relationship with queues
 */
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id", nullable = false)
    private long courseId;

    @Column(name = "course_code", nullable = false)
    private String courseCode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "expected_end_date", nullable = false)
    private Date expectedEndDate;

    @Column(name = "number_of_assignments", nullable = false)
    private int numberOfAssignments;

    @Column(name = "min_approved_assignments", nullable = false)
    private int minApprovedAssignments;

    @Column(name = "number_parts_assignments", nullable = false)
    private int numberPartsAssignments;

    @Column(name = "archived", nullable = false)
    private boolean archived;

    // Many-to-many with student
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    // Many-to-many with teacher
    @ManyToMany(mappedBy = "courses")
    private Set<Teacher> teachers = new HashSet<>();

    // Many-to-many with student in a student assistant relationship
    @ManyToMany(mappedBy = "courses")
    private Set<Student> studentAssistants = new HashSet<>();

    // One-to-many relationship with group of assignments
    @OneToMany(mappedBy = "course")
    private Set<GroupOfAssignment> groupsOfAssignments = new HashSet<>();

    // Many-to-one relationship with administrator
    @ManyToOne
    @JoinColumn(name = "id")
    private Administrator administrator;

    // One-to-many relationship with queue
    @OneToMany(mappedBy = "course")
    private Set<Queue> queues = new HashSet<>();


    // Administrator + todo foreign key
    @Column(name = "teacher_id", nullable = false)
    private long teacherId;
}
