package bank.models;

import org.hibernate.validator.constraints.Currency;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Transaction extends AbstractEntity {

    @NotNull
    @Currency(value = "USD")
    private int amount;

    @NotBlank(message = "Recipient can not be empty.")
    @Size(min = 1, max = 255)
    private String recipient;

    @NotBlank
    @DateTimeFormat
    private LocalDateTime timeStamp = LocalDateTime.now();

    public Transaction() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
