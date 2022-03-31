package ntnu.karolisw.project_backend.repository;

import ntnu.karolisw.project_backend.ProjectBackendApplication;
import ntnu.karolisw.project_backend.model.Administrator;
import ntnu.karolisw.project_backend.model.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProjectBackendApplication.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
class AdministratorRepositoryTest {

    @Autowired private AdministratorRepository adminRepository;
    @Autowired DataSource dataSource;
    @Autowired private EntityManager entityManager;


    @BeforeEach
    void setUp() {
        Administrator admin = new Administrator();
        admin.setEmail("admin@email.com");
        admin.setFirstName("bob");
        admin.setLastName("bobbert");
        admin.setPronouns("he/him");
        admin.setPassword("password");
        adminRepository.save(admin);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void injectedComponentsAreNotNull() {
        Assertions.assertThat(dataSource).isNotNull();
        Assertions.assertThat(adminRepository).isNotNull();
        Assertions.assertThat(entityManager).isNotNull();
    }

    @Test
    void findByEmailAndLastName() {
        Assertions.assertThat(adminRepository.findByEmailAndLastName("admin@email.com", "bobbert")).isNotNull();
    }

    @Test
    void findByEmail() {
        Assertions.assertThat(adminRepository.findByEmail("admin@email.com")).isNotNull();

    }

    @Test
    void findByEmailAndPassword() {
        Assertions.assertThat(adminRepository.findByEmailAndPassword("admin@email.com", "password")).isNotNull();
    }
}
