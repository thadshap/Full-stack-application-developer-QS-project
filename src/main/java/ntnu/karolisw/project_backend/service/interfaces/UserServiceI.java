package ntnu.karolisw.project_backend.service.interfaces;

import ntnu.karolisw.project_backend.dto.in.PersonIn;
import ntnu.karolisw.project_backend.dto.out.PersonOut;
import ntnu.karolisw.project_backend.model.Administrator;
import ntnu.karolisw.project_backend.model.Student;
import ntnu.karolisw.project_backend.model.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceI {
    byte[] generateRandomSalt();

    byte[] hashPassword(String password, byte[] salt);

    void saveNewUser(String email, String password, long personId, int userBit) throws Exception;

    byte[] getHashingFunction(String email, int userBit);

    PersonOut validatePassword(String email, String password, int userBit) throws IllegalAccessException;

    ResponseEntity<Object> addNewStudent(PersonIn dto);

    ResponseEntity<Object> addNewTeacher(PersonIn dto);

    ResponseEntity<Object> addNewAdministrator(PersonIn dto);

    Student getStudent(long studentId);

    Teacher getTeacher(long teacherId);

    Administrator getAdministrator(long administratorId);

    ResponseEntity<Object> getPronouns(PersonIn dto);

    ResponseEntity<Object> setPronouns(PersonIn dto);
}
