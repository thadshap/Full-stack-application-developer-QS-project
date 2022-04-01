package ntnu.karolisw.project_backend.repository.userRepo;

import ntnu.karolisw.project_backend.model.user.StudentUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentUserRepository extends JpaRepository<StudentUser, Long> {
    Optional<StudentUser> findByEmail(String email);
}
