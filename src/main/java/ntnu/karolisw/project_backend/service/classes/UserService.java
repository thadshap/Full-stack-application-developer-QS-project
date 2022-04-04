package ntnu.karolisw.project_backend.service.classes;

import com.opencsv.CSVWriter;
import ntnu.karolisw.project_backend.dto.in.PersonIn;
import ntnu.karolisw.project_backend.dto.out.PersonOut;
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
import ntnu.karolisw.project_backend.service.interfaces.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService implements UserServiceI {

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
        System.out.println("Newly created salt: " + randomSalt);
        // byte[] hashedPassword = hashPassword(password, generateRandomSalt());
        byte[] hashedPassword = hashPassword(password, randomSalt);

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
    public PersonOut validatePassword(String email, String password, int userBit) throws IllegalAccessException {
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

                    // Create dto
                    PersonOut dto = new PersonOut();

                    // Get student user
                    Student student = studentUserRepository.getStudentByUserId(studentUser.get().getId());
                    dto.setPersonId(student.getId());
                    dto.setLoggedIn(true);
                    return dto;
                } else {
                    throw new IllegalAccessException("The passwords did not match. " +
                            "\n The db password was: " +  Arrays.toString(actualPassword) +
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

                    // Create a dto
                    PersonOut dto = new PersonOut();

                    dto.setPersonId(teacherUser.get().getTeacher().getId());
                    dto.setLoggedIn(true);

                    // Return dto
                    return dto;
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

                    // Create a dto
                    PersonOut dto = new PersonOut();
                    dto.setPersonId(adminUser.get().getAdministrator().getId());
                    dto.setLoggedIn(true);

                    // Return dto
                    return dto;
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

    private static void writeUserToFile(String filePath, long userId, int typeOfUser, String password) {
        try {
            // Create FileWriter object with file as parameter
            FileWriter fileWriter = new FileWriter(filePath, true);

            // Create CSVWriter (dependency = openCsv)
            CSVWriter writer = new CSVWriter(fileWriter);

            // Adding header to csv
            String[] header = {"id", "type", "password"};

            // Write the header to csv
            writer.writeNext(header);

            // add data to csv
            String[] data1 = {String.valueOf(userId), String.valueOf(typeOfUser), password};
            writer.writeNext(data1);

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResponseEntity<Object> addNewStudent(PersonIn dto) {

        // Generate random password using salt method...
        String password = (generateRandomPassword());

        // Generate the salt
        byte [] salt = generateRandomSalt();

        // Create student
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());

        // Create user account for that student
        StudentUser studentUser = new StudentUser();
        studentUser.setSalt(salt);
        studentUser.setPassword(hashPassword(password, salt));
        studentUser.setEmail(dto.getEmail());

        // Create the entities
        studentRepository.save(student);
        studentUserRepository.save(studentUser);

        // Write the password to file!
        writeUserToFile("src/main/resources/static/users.csv",
                studentRepository.findByEmail(dto.getEmail()).get().getId(),
                1, password);

        studentUser.setStudent(student);
        studentUserRepository.save(studentUser);

        // Set the foreign keys
        student.setStudentUser(studentUser);
        studentRepository.save(student);

        System.out.println(studentUserRepository.getById(studentUser.getId()).getStudent().getId());
        System.out.println(studentRepository.getById(student.getId()).getStudentUser().getId());
        System.out.println(studentRepository.getById(student.getId()));
        System.out.println(studentUserRepository.getById(studentUser.getId()));

        // And vice versa
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addNewTeacher(PersonIn dto) {
        // Generate random password using salt method...
        String password = generateRandomPassword();

        // Generate the salt
        byte [] salt = generateRandomSalt();

        // Create teacher
        Teacher teacher = new Teacher();
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setEmail(dto.getEmail());

        // Create user account for that teacher
        TeacherUser teacherUser = new TeacherUser();
        teacherUser.setSalt(salt);
        teacherUser.setPassword(hashPassword(password, salt));
        teacherUser.setEmail(dto.getEmail());

        // Set the foreign keys
        teacherUser.setTeacher(teacher); // todo needed to do both?
        teacher.setTeacherUser(teacherUser);

        teacherRepository.save(teacher);
        teacherUserRepository.save(teacherUser);

        // Write the password to file!
        writeUserToFile("src/main/resources/static/users.csv",
                teacherRepository.findByEmail(dto.getEmail()).get().getId(),
                2, password);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addNewAdministrator(PersonIn dto) {
        // Generate random password using salt method...
        String password = generateRandomPassword();

        // Generate the salt
        byte [] salt = generateRandomSalt();

        // Create administrator
        Administrator administrator = new Administrator();
        administrator.setFirstName(dto.getFirstName());
        administrator.setLastName(dto.getLastName());
        administrator.setEmail(dto.getEmail());

        // Create user account for that administrator
        AdminUser adminUser = new AdminUser();
        adminUser.setSalt(salt);
        adminUser.setPassword(hashPassword(String.valueOf(password), salt));
        adminUser.setEmail(dto.getEmail());

        // Set the foreign keys
        adminUser.setAdministrator(administrator); // todo needed to do both?
        administrator.setAdminUser(adminUser);

        administratorRepository.save(administrator);
        adminUserRepository.save(adminUser);

        // Write the password to file!
        writeUserToFile("src/main/resources/static/users.csv",
                administratorRepository.findByEmail(dto.getEmail()).getId(),
                3, String.valueOf(password));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String generateRandomPassword() {
        Random randomizer = new Random();
        int upperbound = 35000;

        // Return random value
        // return randomizer.nextInt(upperbound);
        return "hei";

    }

    @Override
    public Student getStudent(long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isPresent()) {
            return student.get();
        }
        return null;
    }

    @Override
    public Teacher getTeacher(long teacherId) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if(teacher.isPresent()) {
            return teacher.get();
        }
        else {
            return null;
        }
    }

    @Override
    public Administrator getAdministrator(long administratorId) {
        Optional<Administrator> administrator = administratorRepository.findById(administratorId);
        if(administrator.isPresent()) {
            return administrator.get();
        }
        else {
            return null;

        }
    }

    @Override
    public ResponseEntity<Object> getPronouns(PersonIn dto) {
        if(dto.getTypeOfUser() == 1) {
            Optional<Student> student = studentRepository.findById(dto.getPersonId());
            if(student.isPresent()) {
                return new ResponseEntity<>(student.get().getPronouns(), HttpStatus.OK);
            }
            // If not present
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        }
        else if(dto.getTypeOfUser() == 2) {
            Optional<Teacher> teacher = teacherRepository.findById(dto.getPersonId());
            if(teacher.isPresent()) {
                return new ResponseEntity<>(teacher.get().getPronouns(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        else if(dto.getTypeOfUser() == 3) {
            Optional<Administrator> administrator = administratorRepository.findById(dto.getPersonId());
            if(administrator.isPresent()) {
                return new ResponseEntity<>(administrator.get().getPronouns(), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Sets the pronouns of the user given to the backend in the dto
     * @param dto contains id, type of user, and pronouns of the user that wanted to set them
     *
     * @return OK or NOT_FOUND
     */
    @Override
    public ResponseEntity<Object> setPronouns(PersonIn dto) {
        if(dto.getTypeOfUser() == 1) {
            Student student = studentRepository.getById(dto.getPersonId());
            student.setPronouns(dto.getPronouns());
            studentRepository.save(student);
        }
        else if(dto.getTypeOfUser() == 2) {
            Teacher teacher = teacherRepository.getById(dto.getPersonId());
            teacher.setPronouns(dto.getPronouns());
            teacherRepository.save(teacher);
        }
        else if(dto.getTypeOfUser() == 3) {
            Administrator administrator = administratorRepository.getById(dto.getPersonId());
            administrator.setPronouns(dto.getPronouns());
            administratorRepository.save(administrator);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return null;
    }
}
