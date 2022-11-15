package bank.models;

import org.hibernate.validator.constraints.Currency;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends AbstractEntity {

    @NotNull
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.")
    private String username;

    @NotNull
    private String pwHash;

    @Currency(value = "USD")
    private Integer balance;

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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public boolean verifyPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
