package Esperimento1.ClaudeSonet.smell8Refactored;


import java.util.Objects;
import utility.*;
import Esperimento1.ClaudeSonet.smell8Refactored.classForRefactorLPL.*;

public class BankAccountSmelly {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

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

    public void setupRecurringTransfer(BankAccountSmelly destinationAccount, Money amount, RecurringTransferConfig config) {
        Objects.requireNonNull(destinationAccount, "Destination account must not be null.");
        validatePositiveAmount(amount);
        if (config.frequency.equals("DAILY") || config.frequency.equals("WEEKLY") || config.frequency.equals("MONTHLY")) {
            System.out.println("Recurring transfer set up from " + config.startDate + " to " + config.endDate);
            if (config.notifyOnTransfer) {
                System.out.println("Notifications enabled with max retries: " + config.maxRetries);
            }
        }
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public boolean authorizeTransaction(Money amount, TransactionDetails transactionDetails) {
        validatePositiveAmount(amount);
        if (this.balance.getAmountInCents() < amount.getAmountInCents()) {
            return false;
        }
        if (!transactionDetails.currency.equals("USD")) {
            long convertedAmount = Math.round(amount.getAmountInCents() * transactionDetails.conversionRate);
            amount = Money.ofCents(convertedAmount);
        }
        if (transactionDetails.requiresPin) {
            System.out.println("PIN verification required for transaction: " + transactionDetails.transactionId);
        }
        System.out.println("Transaction authorized at " + transactionDetails.merchantName + " (" + transactionDetails.merchantCategory + ") in " + transactionDetails.location);
        return true;
    }

    public void configureOverdraftProtection(Money overdraftLimit, OverdraftProtectionConfig config) {
        validatePositiveAmount(overdraftLimit);
        if (config.autoTransferFromSavings) {
            Objects.requireNonNull(config.savingsAccount, "Savings account must not be null for auto-transfer.");
        }
        System.out.println("Overdraft protection configured with limit: " + overdraftLimit);
        if (config.notifyOnOverdraft) {
            System.out.println("Notifications will be sent to: " + config.notificationEmail);
        }
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

    @Override
    public String toString() {
        return "BankAccount[accountId=" + accountId +
               ", accountHolder=" + accountHolder +
               ", balance=" + balance + " cents]";
    }
}
