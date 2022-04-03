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
import ntnu.karolisw.project_backend.service.interfaces.SettingServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Contains the methods needed to perform changes to entities as requested from the frontends setting-page.
 * All entities belonging to PERSON superclass uses this service
 */
@Service
public class SettingService implements SettingServiceI {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    AdministratorRepository administratorRepository;

    @Autowired
    StudentUserRepository studentUserRepository;

    @Autowired
    TeacherUserRepository teacherUserRepository;

    @Autowired
    AdminUserRepository adminUserRepository;

    @Autowired
    UserService userService;

    // update last name
    @Override
    public ResponseEntity<Object> updateLastName(long personId, String newLastName, int userBit) throws Exception {
        // If student
        if(userBit == 1) {
            Optional<Student> student = studentRepository.findById(personId);
            // If present
            if(student.isPresent()) {
                student.get().setLastName(newLastName);
                studentRepository.save(student.get());
                return new ResponseEntity<>( HttpStatus.OK);
            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If teacher
        else if(userBit == 2) {
            Optional<Teacher> teacher = teacherRepository.findById(personId);
            // If present
            if(teacher.isPresent()) {
                teacher.get().setLastName(newLastName);
                teacherRepository.save(teacher.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If admin
        else if(userBit == 3) {
            Optional<Administrator> administrator = administratorRepository.findById(personId);
            // If present
            if(administrator.isPresent()) {
                administrator.get().setLastName(newLastName);
                administratorRepository.save(administrator.get());
                return new ResponseEntity<>(administrator.get(), HttpStatus.OK);

            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If the userBit is not correct
        else {
            throw new Exception("The user bit has to be numbers 1-3, not: " + userBit);
        }
    }
    // update first name
    @Override
    public ResponseEntity<Object> updateFirstName(long personId, String newFirstName, int userBit) throws Exception {
        // If student
        if(userBit == 1) {
            Optional<Student> student = studentRepository.findById(personId);
            // If present
            if(student.isPresent()) {
                student.get().setFirstName(newFirstName);
                studentRepository.save(student.get());
                return new ResponseEntity<>(student.get(), HttpStatus.OK);
            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If teacher
        else if(userBit == 2) {
            Optional<Teacher> teacher = teacherRepository.findById(personId);
            // If present
            if(teacher.isPresent()) {
                teacher.get().setFirstName(newFirstName);
                teacherRepository.save(teacher.get());
                return new ResponseEntity<>(teacher.get(), HttpStatus.OK);
            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If admin
        else if(userBit == 3) {
            Optional<Administrator> administrator = administratorRepository.findById(personId);
            // If present
            if(administrator.isPresent()) {
                administrator.get().setFirstName(newFirstName);
                administratorRepository.save(administrator.get());
                return new ResponseEntity<>(administrator.get(), HttpStatus.OK);
            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If the userBit is not correct
        else {
            throw new Exception("The user bit has to be numbers 1-3, not: " + userBit);
        }
    }
    // update email
    @Override
    public ResponseEntity<Object> updateEmail(long personId, String newEmail, int userBit) throws Exception {
        // If student
        if(userBit == 1) {
            Optional<Student> student = studentRepository.findById(personId);
            // If present
            if(student.isPresent()) {
                student.get().setEmail(newEmail);
                studentRepository.save(student.get());
                return new ResponseEntity<>(student.get(), HttpStatus.OK);
            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If teacher
        else if(userBit == 2) {
            Optional<Teacher> teacher = teacherRepository.findById(personId);
            // If present
            if(teacher.isPresent()) {
                teacher.get().setEmail(newEmail);
                teacherRepository.save(teacher.get());
                return new ResponseEntity<>(teacher.get(), HttpStatus.OK);
            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If admin
        else if(userBit == 3) {
            Optional<Administrator> administrator = administratorRepository.findById(personId);
            // If present
            if(administrator.isPresent()) {
                administrator.get().setEmail(newEmail);
                administratorRepository.save(administrator.get());
                return new ResponseEntity<>(administrator.get(), HttpStatus.OK);

            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If the userBit is not correct
        else {
            throw new Exception("The user bit has to be numbers 1-3, not: " + userBit);
        }
    }
    // update pronouns
    @Override
    public ResponseEntity<Object> updatePronouns(long personId, String newPronoun, int userBit) throws Exception {
        // If student
        if(userBit == 1) {
            Optional<Student> student = studentRepository.findById(personId);
            // If present
            if(student.isPresent()) {
                student.get().setPronouns(newPronoun);
                studentRepository.save(student.get());
                return new ResponseEntity<>(student.get(), HttpStatus.OK);
            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If teacher
        else if(userBit == 2) {
            Optional<Teacher> teacher = teacherRepository.findById(personId);
            // If present
            if(teacher.isPresent()) {
                teacher.get().setPronouns(newPronoun);
                teacherRepository.save(teacher.get());
                return new ResponseEntity<>(teacher.get(), HttpStatus.OK);
            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If admin
        else if(userBit == 3) {
            Optional<Administrator> administrator = administratorRepository.findById(personId);
            // If present
            if(administrator.isPresent()) {
                administrator.get().setPronouns(newPronoun);
                administratorRepository.save(administrator.get());
                return new ResponseEntity<>(administrator.get(), HttpStatus.OK);

            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If the userBit is not correct
        else {
            throw new Exception("The user bit has to be numbers 1-3, not: " + userBit);
        }
    }

    // update password
    @Override
    public ResponseEntity<Object> updatePassword(long personId, String newPassword, int userBit) throws Exception {
        // Generate a new hash
        byte[] randomSalt = userService.generateRandomSalt();
        byte[] hashedPassword = userService.hashPassword(newPassword,randomSalt);

        // If student
        if(userBit == 1) {
            Optional<StudentUser> student = studentUserRepository.findById(personId);
            // If present
            if(student.isPresent()) {
                student.get().setSalt(randomSalt);
                student.get().setPassword(hashedPassword);
                studentUserRepository.save(student.get());
                return new ResponseEntity<>(student.get(), HttpStatus.OK);
            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If teacher
        else if(userBit == 2) {
            Optional<TeacherUser> teacher = teacherUserRepository.findById(personId);
            // If present
            if(teacher.isPresent()) {
                teacher.get().setSalt(randomSalt);
                teacher.get().setPassword(hashedPassword);
                teacherUserRepository.save(teacher.get());
                return new ResponseEntity<>(teacher.get(), HttpStatus.OK);
            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If admin
        else if(userBit == 3) {
            Optional<AdminUser> administrator = adminUserRepository.findById(personId);
            // If present
            if(administrator.isPresent()) {
                administrator.get().setSalt(randomSalt);
                administrator.get().setPassword(hashedPassword);
                adminUserRepository.save(administrator.get());
                return new ResponseEntity<>(administrator.get(), HttpStatus.OK);
            }
            else {
                throw new Exception("No administrator present in db with id: " + personId);
            }
        }
        // If the userBit is not correct
        else {
            throw new Exception("The user bit has to be numbers 1-3, not: " + userBit);
        }
    }
}
