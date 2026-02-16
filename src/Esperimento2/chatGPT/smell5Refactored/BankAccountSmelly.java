package Esperimento2.chatGPT.smell5Refactored;


import java.time.LocalTime;
import java.util.Objects;
import utility.AccountHolder;
import utility.AccountID;
import Esperimento2.chatGPT.smell5Refactored.utilityRefactored.*;

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

    /**
     * Delegates to Money's comparison logic to determine if the current balance
     * can cover the given purchase amount.
     */
    public boolean canAffordPurchase(Money purchaseAmount) {
        Objects.requireNonNull(purchaseAmount, "Purchase amount must not be null.");
        return this.balance.isGreaterOrEqualThan(purchaseAmount);
    }

    /**
     * Delegates to Money's comparison logic to determine if the current balance
     * is greater than the given threshold.
     */
    public boolean isBalanceGreaterThan(Money threshold) {
        Objects.requireNonNull(threshold, "Threshold must not be null.");
        return this.balance.isGreaterThan(threshold);
    }

    /**
     * Delegates the responsibility of determining operational status to the
     * BankBranch, which encapsulates its own open/closed and staffing logic.
     */
    public String checkBranchOperationalStatus(LocalTime now) {
        Objects.requireNonNull(now, "Current time must not be null.");
        if (this.homeBranch == null) {
            return "No home branch assigned.";
        }
        return this.homeBranch.getOperationalStatus(now);
    }

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

    public BankBranch getHomeBranch() {
        return homeBranch;
    }

    public void setHomeBranch(BankBranch homeBranch) {
        this.homeBranch = homeBranch;
    }

    @Override
    public String toString() {
        return "BankAccount[accountId=" + accountId +
               ", accountHolder=" + accountHolder +
               ", balance=" + balance + "]";
    }
}