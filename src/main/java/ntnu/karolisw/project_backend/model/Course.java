package ntnu.karolisw.project_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Long courseId;

    @Column(name = "course_code", nullable = false)
    private String courseCode;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "expected_end_date", nullable = false)
    private Date expectedEndDate;

    @Column(name = "number_of_assignments", nullable = false)
    private int numberOfAssignments;

    @Column(name = "min_approved_assignments", nullable = false)
    private int minApprovedAssignments;

    @Column(name = "archived", nullable = false)
    private boolean archived;

    // Many-to-many with student
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    // Many-to-many with teacher
    // If the course is created with teacher as foreign key,
    // then course is added as foreign key to teacher as well
    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "courses")
    private Set<Teacher> teachers = new HashSet<>();

    // Many-to-many with student in a student assistant relationship
    // Remove student assistant objects if the course is removed
    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "taInCourses")
    private Set<Student> studentAssistants = new HashSet<>();

    // One-to-many relationship with group of assignments
    // If the course is deleted or updated, the groups of assignments are too
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "course")
    private Set<GroupOfAssignment> groupsOfAssignments = new HashSet<>();

    // Queue is child, course is parent
    // If the course is deleted, the queue is also deleted
    @OneToOne(mappedBy = "course", cascade = CascadeType.REMOVE)
    private Queue queue;

    // Add a group of assignment to the list of groups of assignments
    public void addGroupOfAssignment(GroupOfAssignment groupOfAssignments) {
        groupsOfAssignments.add(groupOfAssignments);
    }

    // Add a student assistant to a course
    public void addStudentAssistant(Student studentAssistant) {
        studentAssistants.add(studentAssistant);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseCode='" + courseCode + '\'' +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", expectedEndDate=" + expectedEndDate +
                ", numberOfAssignments=" + numberOfAssignments +
                ", minApprovedAssignments=" + minApprovedAssignments +
                ", archived=" + archived +
                ", students=" + students +
                ", teachers=" + teachers +
                ", studentAssistants=" + studentAssistants +
                ", groupsOfAssignments=" + groupsOfAssignments +
                ", queue=" + queue +
                '}';
    }
}
