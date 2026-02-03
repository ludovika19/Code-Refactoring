package Esperimento1.Gemini.smell8Refactored;

import java.util.Objects;
import utility.*;
import Esperimento1.Gemini.smell8Refactored.classForRefactorLPL.*;

public class BankAccount {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

    public BankAccount(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    // Refactored method using RecurringTransferConfig object
    public void setupRecurringTransfer(RecurringTransferConfig config) {
        Objects.requireNonNull(config.getDestinationAccount(), "Destination account must not be null.");
        validatePositiveAmount(config.getAmount());
        String frequency = config.getFrequency();
        if (frequency.equals("DAILY") || frequency.equals("WEEKLY") || frequency.equals("MONTHLY")) {
            System.out.println("Recurring transfer set up from " + config.getStartDate() + " to " + config.getEndDate());
            if (config.shouldNotifyOnTransfer()) {
                System.out.println("Notifications enabled with max retries: " + config.getMaxRetries());
            }
        }
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    // Refactored method using TransactionDetails object
    public boolean authorizeTransaction(TransactionDetails details) {
        Money amount = details.getAmount();
        validatePositiveAmount(amount);
        if (this.balance.getAmountInCents() < amount.getAmountInCents()) {
            return false;
        }
        if (!details.getCurrency().equals("USD")) {
            long convertedAmount = Math.round(amount.getAmountInCents() * details.getConversionRate());
            amount = Money.ofCents(convertedAmount);
        }
        if (details.isPinRequired()) {
            System.out.println("PIN verification required for transaction: " + details.getTransactionId());
        }
        System.out.println("Transaction authorized at " + details.getMerchantName() + " (" + details.getMerchantCategory() + ") in " + details.getLocation());
        return true;
    }

    // Refactored method using OverdraftConfig object
    public void configureOverdraftProtection(OverdraftConfig config) {
        validatePositiveAmount(config.getOverdraftLimit());
        if (config.isAutoTransferFromSavings()) {
            Objects.requireNonNull(config.getSavingsAccount(), "Savings account must not be null for auto-transfer.");
        }
        System.out.println("Overdraft protection configured with limit: " + config.getOverdraftLimit());
        if (config.shouldNotifyOnOverdraft()) {
            System.out.println("Notifications will be sent to: " + config.getNotificationEmail());
        }
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
