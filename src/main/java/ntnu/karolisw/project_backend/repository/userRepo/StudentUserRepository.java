package ntnu.karolisw.project_backend.repository.userRepo;

import ntnu.karolisw.project_backend.model.Student;
import ntnu.karolisw.project_backend.model.user.StudentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentUserRepository extends JpaRepository<StudentUser, Long> {
    // public StudentUser getAStudent();
    // public void create(StudentUser studentUser);

    Optional<StudentUser> findByEmail(String email);

    // get the student of this studentuser
    @Query("SELECT s.student FROM StudentUser s WHERE s.id= :id")
    Student getStudentByUserId(@Param("id") long userId);
}
