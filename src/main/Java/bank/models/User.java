package bank.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User extends AbstractEntity {

    @NotNull
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.")
    private String username;

    @NotNull
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    /* Possible fields:
        Email

        Methods:
        verifyPassword()
     */
}
