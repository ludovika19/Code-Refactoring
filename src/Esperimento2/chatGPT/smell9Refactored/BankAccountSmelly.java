package Esperimento2.chatGPT.smell9Refactored;

import Esperimento2.chatGPT.smell9Refactored.utilityRefactored.*;
import java.util.Objects;
import utility.*;

public class BankAccountSmelly {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;
    private BankBranch homeBranch;

    public BankAccountSmelly(AccountHolder accountHolder, AccountID accountId) {
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

    // --- Refactored methods (message chains removed) ---

    public String getBranchManagerName() {
        // Hide Delegate: BankAccountSmelly no longer traverses manager → personalInfo → name
        return this.homeBranch.getManagerName();
    }

    public String getBranchCity() {
        // Hide Delegate: BankAccountSmelly no longer traverses address → city → name
        return this.homeBranch.getCityName();
    }

    public PhoneNumber getAccountHolderPhoneNumber() {
        // Hide Delegate: BankAccountSmelly no longer traverses contactInfo → primaryPhone → number
        return this.accountHolder.getPrimaryPhoneNumber();
    }

    // --- Remaining methods unchanged ---

    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.subtract(amount);
    }

    public void transferTo(BankAccountSmelly otherAccount, Money amount) {
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