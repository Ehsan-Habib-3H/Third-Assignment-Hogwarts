import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class Account implements AccountManagement {
    private String username;
//    private String password;
    private byte[] password;
    public UUID accountID;

    @Override
    public boolean validatePassword(String enteredPassword) {
        try {
            return authenticate(enteredPassword, password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeUsername(String newUsername) {
        username = newUsername;
        accountID = UUID.randomUUID();
    }

    @Override
    public void changePassword(String newPassword) {
        try {
            password = hashPassword(newPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return username;
    }
    private static byte[] hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }
    private static boolean authenticate(String input, byte[] hashedPassword) throws NoSuchAlgorithmException {
        byte[] hashedInput = hashPassword(input);
        return Arrays.equals(hashedInput, hashedPassword);
    }
}
