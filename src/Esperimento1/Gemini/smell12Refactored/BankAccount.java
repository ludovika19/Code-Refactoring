package Esperimento1.Gemini.smell12Refactored;

import java.util.Objects;
import utility.*;

/**
 * Renamed from BaseAccount to better represent a set of optional features
 * that different account types might have.
 */
class AccountFeatures {
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

/**
 * Refactored to remove inheritance from BaseAccount (now AccountFeatures).
 * By breaking the incorrect "is-a" relationship, we eliminate the need
 * to override and reject inherited methods. This class now only contains
 * methods and properties that are directly relevant to its function.
 */
public class BankAccount {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

    public BankAccount(AccountHolder accountHolder, AccountID accountId) {
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