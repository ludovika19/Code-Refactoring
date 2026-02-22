package Esperimento2.chatGPT.smell12Refactored;


import java.util.Objects;
import utility.*;

class BaseAccount {
    protected String accountCategory;
    protected boolean isInternational;
    
    public void enableInternationalTransactions() {
        this.isInternational = true;
    }
    
    public void setCategory(String category) {
        this.accountCategory = category;
    }
    
    public String getCategory() {
        return this.accountCategory;
    }
    
    public boolean supportsChecks() {
        return true;
    }
    
    public void issueCheckbook(int numberOfChecks) {
        System.out.println("Issuing " + numberOfChecks + " checks");
    }
}


public class BankAccountRefactored {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

    // Optional delegate, if we ever want to reuse BaseAccount behavior
    private final BaseAccount baseAccountFeatures;

    public BankAccountRefactored(AccountHolder accountHolder, AccountID accountId) {
        this(accountHolder, accountId, null);
    }

    // Constructor that allows injecting a delegate (if needed)
    public BankAccountRefactored(AccountHolder accountHolder,
                                 AccountID accountId,
                                 BaseAccount baseAccountFeatures) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
        this.baseAccountFeatures = baseAccountFeatures;
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

    // Explicitly model support for checks – this account type does not support them
    public boolean supportsChecks() {
        return false;
    }

    public void issueCheckbook(int numberOfChecks) {
        // Behavior preserved from original class
        throw new UnsupportedOperationException("This account type does not support checks");
    }

    // Explicitly model international capabilities – not supported for this account
    public boolean supportsInternationalTransactions() {
        return false;
    }

    public void enableInternationalTransactions() {
        // Behavior preserved from original class
        throw new UnsupportedOperationException("International transactions not supported");
    }

    @Override
    public String toString() {
        return "BankAccount[accountId=" + accountId +
               ", accountHolder=" + accountHolder +
               ", balance=" + balance + " cents]";
    }

    // Optional delegation methods if in the future a BankAccount should use BaseAccount behavior
    public void setCategory(String category) {
        if (baseAccountFeatures != null) {
            baseAccountFeatures.setCategory(category);
        }
    }

    public String getCategory() {
        return baseAccountFeatures != null ? baseAccountFeatures.getCategory() : null;
    }
}
