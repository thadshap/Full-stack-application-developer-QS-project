package ntnu.karolisw.project_backend.service.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface LoginServiceI {
    byte[] generateRandomSalt();

    byte[] hashPassword(String password, byte[] salt);

    void saveNewUser(String email, String password, long personId, int userBit) throws Exception;

    byte[] getHashingFunction(String email, int userBit);

    boolean validatePassword(String email, String password, int userBit) throws IllegalAccessException;
}
