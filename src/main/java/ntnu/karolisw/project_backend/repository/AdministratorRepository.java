package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Administrator findByEmailAndLastName(String email, String lastName);
    Administrator findByEmail(String email);
    Administrator findByEmailAndPassword(String email, String password);
}
