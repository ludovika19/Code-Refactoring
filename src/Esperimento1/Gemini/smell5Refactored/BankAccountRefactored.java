package Esperimento1.Gemini.smell5Refactored;

import java.util.Objects;
import Esperimento1.Gemini.smell5Refactored.utilityRefactored.*;

public class BankAccountRefactored {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;
    private BankBranch homeBranch;

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

    /**
     * REFACTORED: This method now delegates the comparison to the Money class.
     */
    public boolean canAffordPurchase(Money purchaseAmount) {
        return this.balance.isGreaterThanOrEqualTo(purchaseAmount);
    }

    /**
     * REFACTORED: This method now delegates the comparison to the Money class.
     */
    public boolean isBalanceGreaterThan(Money threshold) {
        return this.balance.isGreaterThan(threshold);
    }

    /**
     * REMOVED: The checkBranchOperationalStatus method has been moved to the BankBranch class.
     * A client that needs this information should now call it directly on a BankBranch object.
     * For example: `myBankBranch.getOperationalStatus(LocalTime.now());`
     */

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
    
    // Getter for the home branch, so clients can interact with it.
    public BankBranch getHomeBranch() {
        return homeBranch;
    }

    @Override
    public String toString() {
        return "BankAccount[accountId=" + accountId +
               ", accountHolder=" + accountHolder +
               ", balance=" + balance + " cents]";
    }
}
