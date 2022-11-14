package bank.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Transaction extends AbstractEntity {

    @NotNull
    private int amount;

    @NotBlank(message = "Recipient can not be empty.")
    @Size(min = 1, max = 255)
    private String recipient;

    private LocalDateTime timeStamp;
    // Set up way to store/show previous transactions

    // Possible Fields:

    // Date/Time of transaction?            ***Figure out how to log that information.***

    // Possible Methods:

    // addTransaction()?
    // deleteTransaction()?
    // getTransaction()?
}
