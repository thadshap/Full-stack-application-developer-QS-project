package ntnu.karolisw.project_backend.repository.userRepo;

import ntnu.karolisw.project_backend.model.user.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    //  public AdminUser getAnAdmin();
    // public void create(AdminUser adminUser);
    Optional<AdminUser> findByEmail(String email);
}
