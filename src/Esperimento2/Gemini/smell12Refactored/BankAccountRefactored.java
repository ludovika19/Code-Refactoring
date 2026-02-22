package Esperimento2.Gemini.smell12Refactored;

import java.util.Objects;
import utility.*;

// The class no longer extends BaseAccount, breaking the incorrect inheritance.
public class BankAccountRefactored {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;
    
    // Delegation: If this account needed features, it would hold an object that provides them.
    // For this example, we can make it nullable to show it's optional.
    private final AccountFeatures features;

    public BankAccountRefactored(AccountHolder accountHolder, AccountID accountId, AccountFeatures features) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
        this.features = features; // Can be null if no special features are supported.
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    // The methods that were refused are now handled via delegation.
    public boolean supportsChecks() {
        return features != null && features.supportsChecks();
    }

    public void issueCheckbook(int numberOfChecks) {
        if (supportsChecks()) {
            features.issueCheckbook(numberOfChecks);
        } else {
            throw new UnsupportedOperationException("This account type does not support checks");
        }
    }

    public void enableInternationalTransactions() {
        if (features != null) {
            features.enableInternationalTransactions();
        } else {
            throw new UnsupportedOperationException("International transactions not supported");
        }
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
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