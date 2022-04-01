package ntnu.karolisw.project_backend.service.classes;

import ntnu.karolisw.project_backend.model.Administrator;
import ntnu.karolisw.project_backend.model.Student;
import ntnu.karolisw.project_backend.model.Teacher;
import ntnu.karolisw.project_backend.model.user.AdminUser;
import ntnu.karolisw.project_backend.model.user.StudentUser;
import ntnu.karolisw.project_backend.model.user.TeacherUser;
import ntnu.karolisw.project_backend.repository.AdministratorRepository;
import ntnu.karolisw.project_backend.repository.StudentRepository;
import ntnu.karolisw.project_backend.repository.TeacherRepository;
import ntnu.karolisw.project_backend.repository.userRepo.AdminUserRepository;
import ntnu.karolisw.project_backend.repository.userRepo.StudentUserRepository;
import ntnu.karolisw.project_backend.repository.userRepo.TeacherUserRepository;
import ntnu.karolisw.project_backend.service.interfaces.LoginServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Optional;

@Service
public class LoginService implements LoginServiceI {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private StudentUserRepository studentUserRepository;

    @Autowired
    private TeacherUserRepository teacherUserRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AdministratorRepository administratorRepository;


    @Override
    public byte[] generateRandomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    @Override
    public byte[] hashPassword(String password, byte[] salt) {
        // Hashing through the use of SHA-512 and the salt
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-512");
            // Updating the digest using the specified array of bytes
            digest.update(salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // hash the password using the digest
        assert digest != null;
        return digest.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Using the MessageDigest class to configure the SHA-512 hash function to the salt:
     * @param userBit can be numbers 1-3 where:
     *                1 = student
     *                2 = teacher
     *                3 = administrator
     * @param personId is the id of the student/teacher/admin that is creating a new user
     * @param email is the email of the new user
     * @param password is the password that has been validated as a correct new password
     *                 at the frontend, and that now will be hashed before its put into
     *                 the database along with the email and the salt
     */
    @Override
    public void saveNewUser(String email, String password, long personId, int userBit) throws Exception {
        // Generate a hashed password
        byte[] randomSalt = generateRandomSalt();
        byte[] hashedPassword = hashPassword(password, generateRandomSalt());

        // If student
        if(userBit == 1) {
            // Get the student that has the given personId (studentId)
            Optional<Student> student = studentRepository.findById(personId);

            // If the student exists
            if(student.isPresent()) {

                // Create the StudentUser entity
                StudentUser newUser = new StudentUser();
                newUser.setEmail(email);
                newUser.setPassword(hashedPassword);
                newUser.setSalt(randomSalt);

                // Set the foreign key
                newUser.setStudent(student.get());

                // Add the new user to the StudentUserRepository
                studentUserRepository.save(newUser);
            }
            else {
                throw new Exception("There was no student present in db with id: " + personId);
            }
        }
        // If teacher
        else if(userBit == 2) {
            // Get the teacher that has the given personId (studentId)
            Optional<Teacher> teacher = teacherRepository.findById(personId);

            // If the teacher exists
            if(teacher.isPresent()) {

                // Create the TeacherUser entity
                TeacherUser newUser = new TeacherUser();
                newUser.setEmail(email);
                newUser.setPassword(hashedPassword);
                newUser.setSalt(randomSalt);

                // Set the foreign key
                newUser.setTeacher(teacher.get());

                // Add the new teacher to the TeacherUserRepository
                teacherUserRepository.save(newUser);
            }
            else {
                throw new Exception("There was no teacher present in db with id: " + personId);
            }
        }

        // If admin
        else if (userBit == 3) {
            // Get the administrator that has the given personId (studentId)
            Optional<Administrator> administrator = administratorRepository.findById(personId);

            // If the administrator exists
            if(administrator.isPresent()) {

                // Create the StudentUser entity
                AdminUser newUser = new AdminUser();
                newUser.setEmail(email);
                newUser.setPassword(hashedPassword);
                newUser.setSalt(randomSalt);

                // Set the foreign key
                newUser.setAdministrator(administrator.get());

                // Add the new user to the StudentUserRepository
                adminUserRepository.save(newUser);
            }
            else {
                throw new Exception("There was no administrator present in db with id: " + personId);
            }
        }

        // In this case an error has occurred upon reading the userBit
        else {
            throw new Exception("The user bit was not numbers 1-3, but: " + userBit);
        }
    }

    /**
     * Get the salt for the current email in order to validate the password
     *
     * @param email is the email inserted by the user upon login page
     * @param userBit is the bit that signals what type of user this is, where:
     *                1 = StudentUser
     *                2 = TeacherUser
     *                3 = AdminUser
     */
    @Override
    public byte[] getHashingFunction(String email, int userBit) {
        // If student
        if(userBit == 1) {
            Optional<StudentUser> studentUser = studentUserRepository.findByEmail(email);
            if(studentUser.isPresent()) {
                // return the hashing function
                return studentUser.get().getSalt();
            }
            // If there was no student with specified email address
            else {
                throw new IllegalArgumentException("There was no student with the email: " + email);
            }
        }
        // If teacher
        else if(userBit == 2) {
            Optional<TeacherUser> teacherUser = teacherUserRepository.findByEmail(email);
            if(teacherUser.isPresent()) {
                // return the hashing function
                return teacherUser.get().getSalt();
            }
            // If there was no teacher with specified email address
            else {
                throw new IllegalArgumentException("There was no teacher with the email: " + email);
            }
        }
        // If administrator
        else if(userBit == 3) {
            Optional<AdminUser> adminUser = adminUserRepository.findByEmail(email);
            if(adminUser.isPresent()) {
                // return the hashing function
                return adminUser.get().getSalt();
            }
            // If there was no admin with specified email address
            else {
                throw new IllegalArgumentException("There was no administrator with the email: " + email);
            }
        }
        // If none of the above
        else {
            throw new IllegalArgumentException("The userBit was not numbers 1-3, but: " + userBit);
        }
    }



    /**
     * Upon login, this function handles the main functionality through usage of
     * the other methods of this class
     * @param userBit is the bit that signals what type of user this is, where:
     *          1 = StudentUser
     *          2 = TeacherUser
     *          3 = AdminUser
     * @param email is the email inserted by the user that is trying to log in
     * @param password is the password inserted by the user that is trying to log in
     */
    @Override
    public boolean validatePassword(String email, String password, int userBit) throws IllegalAccessException {
        // Get the user
        if (userBit == 1) {
            Optional<StudentUser> studentUser = studentUserRepository.findByEmail(email);

            // If the user is present
            if (studentUser.isPresent()) {
                // Get the salt
                byte[] hashingSalt = studentUser.get().getSalt();
                // Apply the salt to the password
                byte[] hashedPassword = hashPassword(password, hashingSalt);
                // Get the password from the db
                byte[] actualPassword = studentUser.get().getPassword();
                // If the passwords match
                if (Arrays.toString(actualPassword).equals(Arrays.toString(hashedPassword))) {
                    return true;
                } else {
                    throw new IllegalAccessException("The passwords did not match. " +
                            "\n The db password was: " + Arrays.toString(actualPassword) +
                            "\n The input password was: " + Arrays.toString(hashedPassword));
                }
            }
            // If the user is not present
            else {
                throw new IllegalArgumentException("There is no student with email: " + email + " in db. ");
            }
        } else if (userBit == 2) {
            Optional<TeacherUser> teacherUser = teacherUserRepository.findByEmail(email);

            // If the user is present
            if (teacherUser.isPresent()) {
                // Get the salt
                byte[] hashingSalt = teacherUser.get().getSalt();
                // Apply the salt to the password
                byte[] hashedPassword = hashPassword(password, hashingSalt);
                // Get the password from the db
                byte[] actualPassword = teacherUser.get().getPassword();
                // If the passwords match
                if (Arrays.toString(actualPassword).equals(Arrays.toString(hashedPassword))) {
                    return true;
                } else {
                    throw new IllegalAccessException("The passwords did not match. " +
                            "\n The db password was: " + Arrays.toString(actualPassword) +
                            "\n The input password was: " + Arrays.toString(hashedPassword));
                }
            }
            // If the user is not present
            else {
                throw new IllegalArgumentException("There is no teacher with email: " + email + " in db. ");
            }
        } else if (userBit == 3) {
            Optional<AdminUser> adminUser = adminUserRepository.findByEmail(email);

            // If the user is present
            if (adminUser.isPresent()) {
                // Get the salt
                byte[] hashingSalt = adminUser.get().getSalt();
                // Apply the salt to the password
                byte[] hashedPassword = hashPassword(password, hashingSalt);
                // Get the password from the db
                byte[] actualPassword = adminUser.get().getPassword();
                // If the passwords match
                if (Arrays.toString(actualPassword).equals(Arrays.toString(hashedPassword))) {
                    return true;
                } else {
                    throw new IllegalAccessException("The passwords did not match. " +
                            "\n The db password was: " + Arrays.toString(actualPassword) +
                            "\n The input password was: " + Arrays.toString(hashedPassword));
                }
            }
            // If the user is not present
            else {
                throw new IllegalArgumentException("There is no administrator with email: " + email + " in db. ");
            }
        }
        // The userBit was not a valid number
        else {
            throw new IllegalArgumentException("The user bit did not hold a number 1-3, but: " + userBit);
        }
    }
}
