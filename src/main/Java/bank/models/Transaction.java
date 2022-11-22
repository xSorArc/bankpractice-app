package bank.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Transaction extends AbstractEntity {

    @NotNull
//    @Currency(value = "USD")
    private double amount;

    @NotBlank(message = "Recipient can not be empty.")
    @Size(min = 1, max = 255)
    private String recipient;

    @DateTimeFormat
    private LocalDateTime timeStamp;

    @ManyToOne
    private User user;

    public Transaction() {

    }

    public Transaction(double amount, String recipient, LocalDateTime timeStamp) {
        this.amount = amount;
        this.recipient = recipient;
        this.timeStamp = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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
