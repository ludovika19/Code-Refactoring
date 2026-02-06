package Esperimento1.Gemini.smell11Refactored;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import utility.*;
import Esperimento1.Gemini.smell11Refactored.classForRefactorPROBS.*;

public class BankAccount {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;
    private AccountStatus accountStatus;
    private AccountType accountType;

    public BankAccount(AccountHolder accountHolder, AccountID accountId) {
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
        
        if (this.accountStatus != AccountStatus.CLOSED) {
            this.balance = this.balance.add(amount);
        }
    }

    public String generateAccountStatement(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder statement = new StringBuilder();
        statement.append("=== ACCOUNT STATEMENT ===\n");
        statement.append("Account ID: ").append(this.accountId).append("\n");
        statement.append("Account Holder: ").append(this.accountHolder).append("\n");
        statement.append("Statement Period: ").append(startDate.format(formatter)).append(" to ").append(endDate.format(formatter)).append("\n");
        statement.append("Current Balance: ").append(this.balance).append("\n");
        statement.append("------------------------\n");

        statement.append("Account Type: ").append(this.accountType.getDescription()).append("\n");
        
        statement.append("Interest Rate: 0.00%\n");
        statement.append("Monthly Fee: $0.00\n");
        statement.append("Overdraft Protection: No\n");
        statement.append("========================\n");
        return statement.toString();
    }

    public void setAccountStatus(AccountStatus status) {
        this.accountStatus = Objects.requireNonNull(status, "Status cannot be null.");
    }

    public double getTransactionFee(TransactionType transactionType) {
        return transactionType.getFee();
    }

    public int getMaxDailyWithdrawals(AccountTier accountTier) {
        return accountTier.getMaxDailyWithdrawals();
    }

    public String getRewardMultiplier(CardType cardType) {
        return cardType.getRewardMultiplier();
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
