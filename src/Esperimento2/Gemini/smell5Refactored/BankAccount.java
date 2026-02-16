package Esperimento2.Gemini.smell5Refactored;

import java.util.Objects;
import utility.AccountHolder;
import utility.AccountID;
import Esperimento2.Gemini.smell5Refactored.utilityRefactored.*;

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
    
    // Setter for homeBranch to make the class testable
    public void setHomeBranch(BankBranch homeBranch) {
        this.homeBranch = homeBranch;
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

    /**
     * Refactored: Now delegates the comparison to the Money class.
     */
    public boolean canAffordPurchase(Money purchaseAmount) {
        return this.balance.isGreaterThanOrEqual(purchaseAmount);
    }

    /**
     * Refactored: Now delegates the comparison to the Money class.
     */
    public boolean isBalanceGreaterThan(Money threshold) {
        return this.balance.isGreaterThan(threshold);
    }

    /**
     * Refactored: The envious method was moved to BankBranch.
     * The BankAccount now just asks the BankBranch for its status.
     */
    public String checkBranchOperationalStatus(java.time.LocalTime now) {
        return this.homeBranch.getOperationalStatus(now);
    }

    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
        if (!canAffordPurchase(amount)) {
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

    @Override
    public String toString() {
        return "BankAccount[accountId=" + accountId +
               ", accountHolder=" + accountHolder +
               ", balance=" + balance + " cents]";
    }
}
