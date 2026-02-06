package Esperimento1.ClaudeSonet.smell11Refactored;

import java.util.Objects;
import utility.*;

public class BankAccountRefactored {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;
    private AccountStatus accountStatus;
    private int accountTypeCode;

    public BankAccountRefactored(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
        this.accountStatus = AccountStatus.ACTIVE;
        this.accountTypeCode = 1;
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        
        if (this.accountStatus.allowsDeposits()) {
            this.balance = this.balance.add(amount);
        }
    }

    public String generateAccountStatement(DateRange period) {
        StringBuilder statement = new StringBuilder();
        statement.append("=== ACCOUNT STATEMENT ===\n");
        statement.append("Account ID: ").append(this.accountId).append("\n");
        statement.append("Account Holder: ").append(this.accountHolder).append("\n");
        statement.append("Statement Period: ").append(period).append("\n");
        statement.append("Current Balance: ").append(this.balance).append("\n");
        statement.append("------------------------\n");

        if (this.accountTypeCode == 1) {
            statement.append("Account Type: Checking\n");
        } else if (this.accountTypeCode == 2) {
            statement.append("Account Type: Savings\n");
        } else {
            statement.append("Account Type: Business\n");
        }
        
        statement.append("Interest Rate: 0.00%\n");
        statement.append("Monthly Fee: $0.00\n");
        statement.append("Overdraft Protection: No\n");
        statement.append("========================\n");
        return statement.toString();
    }

    public void setAccountStatus(AccountStatus status) {
        this.accountStatus = Objects.requireNonNull(status, "Account status must not be null.");
    }

    public double getTransactionFee(TransactionType transactionType) {
        Objects.requireNonNull(transactionType, "Transaction type must not be null.");
        return transactionType.getFee();
    }

    public int getMaxDailyWithdrawals(AccountTier accountTier) {
        Objects.requireNonNull(accountTier, "Account tier must not be null.");
        return accountTier.getMaxDailyWithdrawals();
    }

    public String getRewardMultiplier(CardType cardType) {
        Objects.requireNonNull(cardType, "Card type must not be null.");
        return cardType.getRewardMultiplier();
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
