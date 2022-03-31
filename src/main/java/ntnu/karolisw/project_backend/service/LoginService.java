package ntnu.karolisw.project_backend.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class LoginService {

    // salt password

    // validate user login --> do this in service
    // logout

    public byte[] generateRandomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

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
     * @param email is the email of the new user
     * @param password is the password that has been validated as a correct new password
     *                 at the frontend, and that now will be hashed before its put into
     *                 the database along with the email and the salt
     */
    public void saveNewUser(String email, String password) {
        // Generate a hashed password
        byte[] randomSalt = generateRandomSalt();
        byte[] hashedPassword = hashPassword(password, generateRandomSalt());

        // todo save the email, password and hash


    }

    /**
     * Get the salt for the current email in order to validate the password
     *
     * @param email is the email inserted by the user upon login page
     */
    public byte[] getHashingFunction(String email) {
        // get the hashing function of the inserted email
        // return the hashing function
    }



    /**
     *
     * @param email
     * @param password
     */
    public boolean validatePassword(String email, String password) {
        byte[] hashingSalt = getHashingFunction(email);
        byte[] hashedPassword = hashPassword(password, hashingSalt);

        // todo check if the hashed password matches the one in the db --> in that case return true
    }

}
