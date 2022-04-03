package ntnu.karolisw.project_backend.repository.userRepo;

import ntnu.karolisw.project_backend.model.user.TeacherUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherUserRepository extends JpaRepository<TeacherUser, Long> {
    // public TeacherUser getATeacher();
    // public void create(TeacherUser teacher);
    Optional<TeacherUser> findByEmail(String email);

}
