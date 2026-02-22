package Esperimento2.chatGPT.smell11Refactored;

import java.util.Objects;
import utility.*;
import Esperimento2.chatGPT.smell11Refactored.classForRefactorPROBS.*;

public class BankAccountRefactored {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

    // Replaced String primitive with enum AccountStatus
    private AccountStatus accountStatus;

    // Replaced int primitive with enum AccountType
    private AccountType accountType;

    public BankAccountRefactored(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
        this.accountStatus = AccountStatus.ACTIVE;
        this.accountType = AccountType.CHECKING;
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);

        // use domain behavior instead of literal comparison
        if (!this.accountStatus.isClosed()) {
            this.balance = this.balance.add(amount);
        }
    }

    // Introduced parameter object StatementPeriod
    public String generateAccountStatement(StatementPeriod period) {
        StringBuilder statement = new StringBuilder();
        statement.append("=== ACCOUNT STATEMENT ===\n");
        statement.append("Account ID: ").append(this.accountId).append("\n");
        statement.append("Account Holder: ").append(this.accountHolder).append("\n");
        statement.append("Statement Period: ").append(period).append("\n");
        statement.append("Current Balance: ").append(this.balance).append("\n");
        statement.append("------------------------\n");

        // use AccountType instead of numeric codes
        statement.append("Account Type: ").append(this.accountType.getDescription()).append("\n");

        statement.append("Interest Rate: 0.00%\n");
        statement.append("Monthly Fee: $0.00\n");
        statement.append("Overdraft Protection: No\n");
        statement.append("========================\n");
        return statement.toString();
    }

    // keep compatibility with original API if needed:
    public String generateAccountStatement(String startDate, String endDate) {
        return generateAccountStatement(new StatementPeriod(startDate, endDate));
    }

    // Replace string-based status with type-safe enum
    public void setAccountStatus(AccountStatus status) {
        this.accountStatus = Objects.requireNonNull(status, "Account status must not be null.");
    }

    // Overload to preserve original string API, delegating to enum
    public void setAccountStatus(String status) {
        this.accountStatus = AccountStatus.fromString(status);
    }

    // Replace string type code with enum
    public double getTransactionFee(TransactionType transactionType) {
        switch (transactionType) {
            case WIRE:
                return 25.00;
            case ATM:
                return 2.50;
            case TRANSFER:
                return 0.00;
            case CHECK:
                return 1.00;
            default:
                return 5.00;
        }
    }

    // Compatibility wrapper for original string-based method
    public double getTransactionFee(String transactionType) {
        return getTransactionFee(TransactionType.fromString(transactionType));
    }

    public int getMaxDailyWithdrawals(AccountTier accountTier) {
        switch (accountTier) {
            case BASIC:
                return 3;
            case STANDARD:
                return 5;
            case PREMIUM:
                return 10;
            case VIP:
                return -1;
            default:
                return 1;
        }
    }

    public int getMaxDailyWithdrawals(String accountTier) {
        return getMaxDailyWithdrawals(AccountTier.fromString(accountTier));
    }

    public String getRewardMultiplier(CardType cardType) {
        // logic is now encapsulated in CardType
        return cardType.rewardMultiplier();
    }

    public String getRewardMultiplier(String cardType) {
        return getRewardMultiplier(CardType.fromString(cardType));
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
               ", type=" + accountType + "]";
    }
}