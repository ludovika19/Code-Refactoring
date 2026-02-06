package Esperimento1.chatGPT.smell11Refactored;

import java.util.Objects;
import utility.*;
import Esperimento1.chatGPT.smell11Refactored.classForRefactorPROBS.*;

public class BankAccountRefactored {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

    // Replace primitive String status with a dedicated value object
    private AccountStatus accountStatus;

    // Replace primitive type code with a dedicated type object
    private AccountType accountType;

    public BankAccountRefactored(AccountHolder accountHolder, AccountID accountId, AccountType accountType) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);

        // Default ACTIVE status as a domain object, not a raw string
        this.accountStatus = AccountStatus.ACTIVE;

        // Avoid magic number 1; use explicit type object
        this.accountType = Objects.requireNonNull(accountType, "Account type must not be null.");
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);

        // Replace string comparison with behavior on the value object
        if (!this.accountStatus.isClosed()) {
            this.balance = this.balance.add(amount);
        }
    }

    public String generateAccountStatement(StatementPeriod period) {
        StringBuilder statement = new StringBuilder();
        statement.append("=== ACCOUNT STATEMENT ===\n");
        statement.append("Account ID: ").append(this.accountId).append("\n");
        statement.append("Account Holder: ").append(this.accountHolder).append("\n");
        statement.append("Statement Period: ").append(period.format()).append("\n");
        statement.append("Current Balance: ").append(this.balance).append("\n");
        statement.append("------------------------\n");

        // Replace type code + conditionals with behavior on AccountType
        statement.append("Account Type: ").append(this.accountType.getDisplayName()).append("\n");

        statement.append("Interest Rate: ").append(this.accountType.getInterestRateDescription()).append("\n");
        statement.append("Monthly Fee: ").append(this.accountType.getMonthlyFeeDescription()).append("\n");
        statement.append("Overdraft Protection: ").append(this.accountType.getOverdraftProtectionDescription()).append("\n");
        statement.append("========================\n");
        return statement.toString();
    }

    public void setAccountStatus(AccountStatus status) {
        // validation of allowed states is encapsulated by the enum/value object itself
        this.accountStatus = Objects.requireNonNull(status, "Account status must not be null.");
    }

    public Money getTransactionFee(TransactionType transactionType) {
        Objects.requireNonNull(transactionType, "Transaction type must not be null.");
        // Replace switch on string with behavior on TransactionType
        return transactionType.getFee();
    }

    public int getMaxDailyWithdrawals(AccountTier accountTier) {
        Objects.requireNonNull(accountTier, "Account tier must not be null.");
        // Replace switch on string with behavior on AccountTier
        return accountTier.getMaxDailyWithdrawals();
    }

    public String getRewardMultiplier(CardType cardType) {
        Objects.requireNonNull(cardType, "Card type must not be null.");
        // Replace switch on string with behavior on CardType
        return cardType.getRewardMultiplierDescription();
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

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        return "BankAccount[accountId=" + accountId +
               ", accountHolder=" + accountHolder +
               ", balance=" + balance + " cents" +
               ", status=" + accountStatus +
               ", type=" + accountType +
               "]";
    }
}
