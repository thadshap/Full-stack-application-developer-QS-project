package ntnu.karolisw.project_backend.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SettingServiceI {
    // update last name
    ResponseEntity<Object> updateLastName(long personId, String newLastName, int userBit) throws Exception;

    // update first name
    ResponseEntity<Object> updateFirstName(long personId, String newFirstName, int userBit) throws Exception;

    // update email
    ResponseEntity<Object> updateEmail(long personId, String newEmail, int userBit) throws Exception;

    // update pronouns
    ResponseEntity<Object> updatePronouns(long personId, String newPronoun, int userBit) throws Exception;

    // update password
    ResponseEntity<Object> updatePassword(long personId, String newPassword, int userBit) throws Exception;
}
