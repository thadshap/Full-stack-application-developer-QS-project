package ntnu.karolisw.project_backend.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ntnu.karolisw.project_backend.model.Student;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Class for use during login
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_users")
public class StudentUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    // The password is hashed
    @Column(name = "password", nullable = false)
    private byte[] password;

    // The salt
    @Column(name = "salt", nullable = false)
    private byte[] salt;

    // one-to-one between user account and the person entity
    // One-to-one connection with student
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password=" + Arrays.toString(password) +
                ", salt=" + Arrays.toString(salt) +
                ", student=" + student +
                '}';
    }
}
