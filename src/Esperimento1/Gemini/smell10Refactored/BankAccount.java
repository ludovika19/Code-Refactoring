package Esperimento1.Gemini.smell10Refactored;

import java.util.Objects;
import utility.*;

public class BankAccount {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;
    // The following fields are no longer needed in this class after refactoring
    // private TransactionLogger transactionLogger;
    // private AccountSecurityManager securityManager;
    // private NotificationService notificationService;

    public BankAccount(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
        // Assuming these services would be managed elsewhere, e.g., injected or retrieved by the client
        // this.transactionLogger = new TransactionLogger();
        // this.securityManager = new AccountSecurityManager();
        // this.notificationService = new NotificationService();
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    // REFACTORED: The method checkSecurityAlert() was removed.
    // The client should get the AccountSecurityManager and call hasAlert() directly.
    /*
    public boolean checkSecurityAlert(AccountID accountId) {
        return this.securityManager.hasAlert(accountId);
    }
    */

    // REFACTORED: The method sendNotification() was removed.
    // The client should get the NotificationService and call send() directly.
    /*
    public void sendNotification(NotificationMessage message) {
        this.notificationService.send(message);
    }
    */

    // REFACTORED: The method getTransactionHistory() was removed.
    // The client should get the TransactionLogger and call getHistory() directly.
    /*
    public TransactionHistory getTransactionHistory() {
        return this.transactionLogger.getHistory();
    }
    */

    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.subtract(amount);
    }

    public void transferTo(BankAccount otherAccount, Money amount) {
        Objects.requireNonNull(otherAccount, "Destination account must not be null.");

        this.withdraw(amount);
        otherAccount.deposit(amount);
    }

    public Money withdrawAll() {
        Money amount = this.balance;
        this.balance = Money.ofCents(0);
        return amount;
    }

    public Money getBalance() {
        return this.balance;
    }

    public AccountHolder getAccountHolder() {
        return this.accountHolder;
    }

    public AccountID getAccountId() {
        return this.accountId;
    }

    @Override
    public String toString() {
        return "BankAccount[accountId=" + accountId +
               ", accountHolder=" + accountHolder +
               ", balance=" + balance + " cents]";
    }
}
