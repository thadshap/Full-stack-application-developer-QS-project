package ntnu.karolisw.project_backend.service.classes;

import ntnu.karolisw.project_backend.model.user.StudentUser;
import ntnu.karolisw.project_backend.repository.userRepo.StudentUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

// @ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@DataJpaTest
class LoginServiceTest {

    /**

    @InjectMocks
    private LoginService loginService; // TODO TRY USING INTERFACE

    @InjectMocks
    private CourseService courseService; // TODO TRY USING INTERFACE

    @Mock
    private AdminUserRepository adminUserRepo;

    @Mock
    private StudentUserRepository studentUserRepo;

    @Mock
    private TeacherUserRepository teacherUserRepo;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;
    /**
     * Create the model-entities needed
     *
    @BeforeEach
    public void setUp() {
        byte[] salt = loginService.generateRandomSalt();
        byte[] salt2 = loginService.generateRandomSalt();
        byte[] salt3 = loginService.generateRandomSalt();


        // All 4 needed to initialize the users properly // todo might have to initialize further
        Administrator admin = new Administrator();
        Teacher teacher = new Teacher();
        Student student = new Student();
        student.setId(9L);
        Course course = new Course();

        // Add their attributes
        AdminUser adminUser= new AdminUser(1L,"karo.wa@hot", loginService.hashPassword("123",salt ),
                salt, admin);
        TeacherUser teacherUser= new TeacherUser(2L, "bla@mail",
                loginService.hashPassword("hihi", salt2),salt2, teacher);
        StudentUser studentUser= new StudentUser(3L, "rrrr@gm",
                loginService.hashPassword("hoga", salt3),salt3, student);

        Mockito.lenient().when(adminUserRepo.getAnAdmin()).thenReturn(adminUser);
        Mockito.lenient().when(teacherUserRepo.getATeacher()).thenReturn(teacherUser);
        Mockito.lenient().when(studentUserRepo.getAStudent()).thenReturn(studentUser);
        Mockito.lenient().when(studentRepository.getAStudent()).thenReturn(student);


        lenient().doNothing().when(adminUserRepo).create(adminUser);
        lenient().doNothing().when(teacherUserRepo).create(teacherUser);
        lenient().doNothing().when(studentUserRepo).create(studentUser);
        lenient().doNothing().when(studentRepository).create(student);

    }


    /**
     * Salts should be very different!
     *
    @Test
    void generateRandomSalt() {
        assertNotEquals(loginService.generateRandomSalt(), loginService.generateRandomSalt());
    }

    @Test
    void hashPassword() {
        // generate a salt
        byte[] salt = loginService.generateRandomSalt();
        // Create a password
        String password = "password";
        // Hash the password
        byte[] hashedPassword = loginService.hashPassword(password,salt);

        assertEquals(Arrays.toString(hashedPassword),
                Arrays.toString(loginService.hashPassword(password, salt)));
    }

    @Test
    void saveNewUser() {
        StudentUser newUser = new StudentUser();
        Student student = new Student();
        student.setId(9L);
        student.setStudentUser(newUser);

        studentRepository.save(student);

        byte[] salt = loginService.generateRandomSalt();
        newUser.setEmail("email.com");
        newUser.setStudent(student);
        newUser.setPassword(loginService.hashPassword("password", salt));
        newUser.setSalt(salt);
        newUser.setId(1L);
        studentUserRepo.create(newUser);

        try {
            loginService.saveNewUser(newUser.getEmail(), Arrays.toString(newUser.getPassword()), 9, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getHashingFunction() {
    }

    @Test
    void validatePassword() {
    }
    */
}
