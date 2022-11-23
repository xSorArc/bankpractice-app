package bank.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotNull(message = "Username required!")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.")
    private String username;

    @NotNull
    private String pwHash;

    //    @Currency(value = "USD")
    private Integer balance = 0;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public boolean checkPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
