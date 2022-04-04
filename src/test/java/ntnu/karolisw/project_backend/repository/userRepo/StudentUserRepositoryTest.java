package ntnu.karolisw.project_backend.repository.userRepo;

import ntnu.karolisw.project_backend.model.user.StudentUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class StudentUserRepositoryTest {

    /**
     * The purpose of the EntityManager is to interact with the persistence context.
     * TestEntityManager allows us to use EntityManager in tests.
     */
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentUserRepository studentUserRepository;


    @Test
    public void shouldStoreUser() {
        StudentUser student = new StudentUser();
        student.setEmail("karoline.wahl@mail.com");
        student.setSalt("salt123".getBytes(StandardCharsets.UTF_8));
        student.setPassword("123".getBytes(StandardCharsets.UTF_8));

        studentUserRepository.save(student);

        // Assert that user is stored
        List<StudentUser> students = studentUserRepository.findAll();
        assertThat(students).isNotEmpty();
    }
    @Test
    public void shouldFindAllUsers() {

        // User #1
        StudentUser student = new StudentUser();
        student.setEmail("karoline.wahl@mail.com");
        student.setSalt("salt123".getBytes(StandardCharsets.UTF_8));
        student.setPassword("123".getBytes(StandardCharsets.UTF_8));

        studentUserRepository.save(student);

        // User #2
        StudentUser student2 = new StudentUser();
        student.setEmail("eirin.baerin@mail.com");
        student.setSalt("salt123".getBytes(StandardCharsets.UTF_8));
        student.setPassword("456".getBytes(StandardCharsets.UTF_8));

        studentUserRepository.save(student);

        // Assert that there are two entities stored
        List<StudentUser> students = studentUserRepository.findAll();
        assertNotEquals(students.size(), 0);
    }
    @Test
    public void shouldFindUserById() {
        StudentUser student = new StudentUser();
        student.setEmail("karoline.wahl@mail.com");
        student.setSalt("salt123".getBytes(StandardCharsets.UTF_8));
        student.setPassword("123".getBytes(StandardCharsets.UTF_8));

        studentUserRepository.save(student);

        // Get student id
        Optional<StudentUser> student2 = studentUserRepository.findByEmail("karoline.wahl@mail.com");
        long id = student2.get().getId();

        // Assert that id != 0
        assertThat(id).isGreaterThan(0);
    }

    @Test
    public void shouldDeleteUserById() {
        StudentUser student = new StudentUser();
        student.setEmail("karoline.wahl@mail.com");
        student.setSalt("salt123".getBytes(StandardCharsets.UTF_8));
        student.setPassword("123".getBytes(StandardCharsets.UTF_8));

        studentUserRepository.save(student);

        // Get student id
        Optional<StudentUser> student2 = studentUserRepository.findByEmail("karoline.wahl@mail.com");
        long id = student2.get().getId();

        // Delete student
        studentUserRepository.delete(student2.get());

        // Assert that it is not in repository
        assertThat(studentUserRepository.findById(id)).isEmpty();
    }
}
