package ntnu.karolisw.project_backend.service.interfaces;

import ntnu.karolisw.project_backend.dto.out.PersonOut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LoginServiceI {
    byte[] generateRandomSalt();

    byte[] hashPassword(String password, byte[] salt);

    void saveNewUser(String email, String password, long personId, int userBit) throws Exception;

    byte[] getHashingFunction(String email, int userBit);

    PersonOut validatePassword(String email, String password, int userBit) throws IllegalAccessException;
}
