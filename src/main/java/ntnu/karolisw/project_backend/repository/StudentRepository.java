package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Assignment;
import ntnu.karolisw.project_backend.model.Course;
import ntnu.karolisw.project_backend.model.Student;
import ntnu.karolisw.project_backend.model.user.StudentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmailAndLastName(String email, String lastName);
    Optional<Student> findByEmail(String email);
    // public Student getAStudent();
    // public void create(Student student);


    // get all courses where student id = x
    @Query("SELECT s.courses FROM Student s WHERE s.id = :id")
    Set<Course> getCoursesByStudentId(@Param("id") long id);

    // get all assignments where student id = y
    @Query("SELECT s.assignments FROM Student s WHERE s.id = :id")
    Set<Assignment> getAssignmentsByStudentId(@Param("id") long id);

    // get studentUser by email
    @Query("SELECT s.studentUser FROM Student s WHERE s.email = :email")
    StudentUser getStudentUser(@Param("email") String email);

}
