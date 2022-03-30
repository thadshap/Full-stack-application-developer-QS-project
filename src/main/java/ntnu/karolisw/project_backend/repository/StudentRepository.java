package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Course;
import ntnu.karolisw.project_backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmailAndLastName(String email, String lastName);
    Student findByEmail(String email, String lastName);
    Student findByEmailAndPassword(String email, String password);

    // get all students and where course id = x
    // todo create query!
    List<Student> findByCourse(Course course);

}
