package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Assignment;
import ntnu.karolisw.project_backend.model.Course;
import ntnu.karolisw.project_backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmailAndLastName(String email, String lastName);
    Student findByEmail(String email);
    Student findByEmailAndPassword(String email, String password);

    // get all courses where student id = x
    @Query("SELECT s.courses FROM Student s WHERE s.id = :id")
    Set<Course> getCoursesByStudentId(@Param("id") long id);

    // get all assignments where student id = y
    @Query("SELECT s.assignments FROM Student s WHERE s.id = :id")
    Set<Assignment> getAssignmentsByStudentId(@Param("id") long id);
}
