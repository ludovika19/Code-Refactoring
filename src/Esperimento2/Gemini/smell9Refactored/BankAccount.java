package Esperimento2.Gemini.smell9Refactored;

import java.util.Objects;
import utility.AccountID;
import utility.Money;
import Esperimento2.Gemini.smell9Refactored.utilityRefactored.*;

public class BankAccount {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;
    private BankBranch homeBranch;

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

    // REFACTORED: Calls the new delegating method in BankBranch
    public String getBranchManagerName() {
        return this.homeBranch.getManagerName();
    }

    // REFACTORED: Calls the new delegating method in BankBranch
    public String getBranchCity() {
        return this.homeBranch.getCityName();
    }

    // REFACTORED: Calls the new delegating method in AccountHolder
    public PhoneNumber getAccountHolderPhoneNumber() {
        return this.accountHolder.getPrimaryPhoneNumber();
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
    
    // Setter for homeBranch to make the class testable
    public void setHomeBranch(BankBranch homeBranch) {
        this.homeBranch = homeBranch;
    }

    @Override
    public String toString() {
        return "BankAccount[accountId=" + accountId +
               ", accountHolder=" + accountHolder +
               ", balance=" + balance + " cents]";
    }
}