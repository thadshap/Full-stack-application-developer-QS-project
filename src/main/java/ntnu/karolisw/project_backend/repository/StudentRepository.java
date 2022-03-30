package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // find user by username
    // SELECT student FROM studenter WHERE email=?



}
