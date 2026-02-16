package Esperimento2.Gemini.smell10Refactored;


import java.util.Objects;
import utility.*;

public class BankAccount {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;
    private TransactionLogger transactionLogger;
    private AccountSecurityManager securityManager;
    private NotificationService notificationService;

    public BankAccount(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
        // Initialize delegate objects. In a real application, these might be injected.
        this.transactionLogger = new TransactionLogger();
        this.securityManager = new AccountSecurityManager();
        this.notificationService = new NotificationService();
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

    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
        if (this.balance.isLessThan(amount)) {
            throw new IllegalStateException("Insufficient funds for withdrawal.");
        }
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
    
    // Getters for delegate objects
    public TransactionLogger getTransactionLogger() {
        return this.transactionLogger;
    }
    
    public AccountSecurityManager getSecurityManager() {
        return this.securityManager;
    }

    public NotificationService getNotificationService() {
        return this.notificationService;
    }

    @Override
    public String toString() {
        return "BankAccount[accountId=" + accountId +
               ", accountHolder=" + accountHolder +
               ", balance=" + balance + " cents]";
    }
}
