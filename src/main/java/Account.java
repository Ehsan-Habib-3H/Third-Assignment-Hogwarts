import java.util.Objects;
import java.util.UUID;

public class Account implements AccountManagement {
    private String username;
    // TODO: Passwords should hashed
    private String password;
    public UUID accountID;

    @Override
    public boolean validatePassword(String enteredPassword) {
        //TODO
        return (Objects.equals(password, enteredPassword));
    }

    @Override
    public void changeUsername(String newUsername) {
        username = newUsername;
        accountID = UUID.randomUUID();
    }

    @Override
    public void changePassword(String newPassword) {
        password = newPassword;
    }

    public String getUsername() {
        return username;
    }
}
