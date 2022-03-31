package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Course;
import ntnu.karolisw.project_backend.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByEmailAndLastName(String email, String lastName);
    Teacher findByEmail(String email);
    Teacher findByEmailAndPassword(String email, String password);

    // get all courses where teacher id = x
    @Query("SELECT t.courses FROM Teacher t WHERE t.id = :id")
    Set<Course> getCoursesByTeacherId(@Param("id") long id);
}
