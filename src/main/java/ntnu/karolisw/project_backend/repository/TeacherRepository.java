package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // get teacher where email =
    // get all courses where teacher id =
    // get all teachers where course id =

}
