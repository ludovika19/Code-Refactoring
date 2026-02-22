package Esperimento2.Gemini.smell11Refactored;

import java.util.Objects;
import Esperimento2.Gemini.smell11Refactored.classrorrefactorprobs.*;
import utility.*;

public class BankAccountRefactored {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;
    private AccountStatus accountStatus; // Replaced String
    private AccountType accountType;     // Replaced int

    public BankAccountRefactored(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
        this.accountStatus = AccountStatus.ACTIVE;
        this.accountType = AccountType.fromCode(1); // Use enum factory
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        
        if (!this.accountStatus.isClosed()) { // Use method on enum
            this.balance = this.balance.add(amount);
        }
    }

    // Replaced String parameters with a Parameter Object
    public String generateAccountStatement(StatementPeriod period) {
        StringBuilder statement = new StringBuilder();
        statement.append("=== ACCOUNT STATEMENT ===\n");
        statement.append("Account ID: ").append(this.accountId).append("\n");
        statement.append("Account Holder: ").append(this.accountHolder).append("\n");
        statement.append("Statement Period: ").append(period).append("\n"); // Use object
        statement.append("Current Balance: ").append(this.balance).append("\n");
        statement.append("------------------------\n");

        // Use property of enum
        statement.append("Account Type: ").append(this.accountType.getDescription()).append("\n");
        
        statement.append("Interest Rate: 0.00%\n");
        statement.append("Monthly Fee: $0.00\n");
        statement.append("Overdraft Protection: No\n");
        statement.append("========================\n");
        return statement.toString();
    }

    // Replaced String parameter with enum
    public void setAccountStatus(AccountStatus status) {
        this.accountStatus = Objects.requireNonNull(status);
    }

    // Replaced String parameter with enum
    public double getTransactionFee(TransactionType transactionType) {
        switch (transactionType) {
            case WIRE: return 25.00;
            case ATM: return 2.50;
            case TRANSFER: return 0.00;
            case CHECK: return 1.00;
            default: return 5.00; // For OTHER
        }
    }

    // Replaced String parameter with enum
    public int getMaxDailyWithdrawals(AccountTier accountTier) {
        switch (accountTier) {
            case BASIC: return 3;
            case STANDARD: return 5;
            case PREMIUM: return 10;
            case VIP: return -1; // Represents unlimited
            default: return 1; // For OTHER
        }
    }

    // Replaced String parameter with enum
    public String getRewardMultiplier(CardType cardType) {
        switch (cardType) {
            case PLATINUM: return "3x points";
            case GOLD: return "2x points";
            case SILVER: return "1.5x points";
            case BRONZE: return "1x points";
            default: return "No rewards"; // For OTHER
        }
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