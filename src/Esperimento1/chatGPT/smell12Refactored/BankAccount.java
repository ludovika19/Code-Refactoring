package Esperimento1.chatGPT.smell12Refactored;

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

/**
 * This class no longer extends BaseAccount.
 * Instead, it uses composition and delegates only the behavior it actually needs.
 */
public class BankAccount {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

    /**
     * Optional: if this type of account does not support checks
     * or international transactions, it simply does not expose
     * such behavior. If needed, a delegate can be added and used
     * selectively (see commented-out field and constructor code).
     */

    // private final BaseAccount baseAccountDelegate;

    public BankAccount(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);

        // Example if delegation was needed:
        // this.baseAccountDelegate = new BaseAccount();
        // this.baseAccountDelegate.setCategory("STANDARD");
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