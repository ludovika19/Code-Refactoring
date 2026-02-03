package Esperimento1.chatGPT.smell10Refactored;

import java.util.Objects;
import utility.*;

public class BankAccountRefactored {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;
    private TransactionLogger transactionLogger;
    private AccountSecurityManager securityManager;
    private NotificationService notificationService;

    public BankAccountRefactored(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
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

    // MIDDLE MAN REMOVED:
    // Clients should now call:
    // account.getSecurityManager().hasAlert(accountId)
    public AccountSecurityManager getSecurityManager() {
        return this.securityManager;
    }

    // MIDDLE MAN REMOVED:
    // Clients should now call:
    // account.getNotificationService().send(message)
    public NotificationService getNotificationService() {
        return this.notificationService;
    }

    // MIDDLE MAN REMOVED:
    // Clients should now call:
    // account.getTransactionLogger().getHistory()
    public TransactionLogger getTransactionLogger() {
        return this.transactionLogger;
    }

    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.subtract(amount);
    }

    public void transferTo(BankAccountRefactored otherAccount, Money amount) {
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
