package bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

    /*  CREATE: user account to keep track of user information.
	    READ: able to display previous and current balances. (Sorted by monthly, bi-monthly, 6 months, year, 5 year?)
	    UPDATE: user info, add funds, remove funds,
	    DELETE: delete account. (delete transaction? Add a "pending" statement to the transaction to show the bank is
	            working on removing the transaction?)

	    FEATURES: 1) STORAGE: DB of user accounts.                       CHECK!
		          2) PROFILE: Current balance, available balance.
		          3) PENDING: Current processing transactions(+/-)
		          4) HISTORY: Show transaction history.
		          5) SAVINGS: Interest on accounts? */
}
