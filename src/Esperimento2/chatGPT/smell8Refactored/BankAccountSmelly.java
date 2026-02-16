package Esperimento2.chatGPT.smell8Refactored;


import java.util.Objects;
import utility.*;
import Esperimento2.chatGPT.smell8Refactored.classForRefactorLPL.*;

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

    public void setupRecurringTransfer(RecurringTransferSettings settings) {
        // destinationAccount null-check is already enforced in settings constructor
        validatePositiveAmount(settings.getAmount());
        String frequency = settings.getFrequency();
        if (frequency.equals("DAILY") || frequency.equals("WEEKLY") || frequency.equals("MONTHLY")) {
            System.out.println("Recurring transfer set up from " + settings.getStartDate() + " to " + settings.getEndDate());
            if (settings.isNotifyOnTransfer()) {
                System.out.println("Notifications enabled with max retries: " + settings.getMaxRetries());
            }
        }
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public boolean authorizeTransaction(TransactionRequest request) {
        Money amount = request.getAmount();
        validatePositiveAmount(amount);
        if (this.balance.getAmountInCents() < amount.getAmountInCents()) {
            return false;
        }
        if (!request.getCurrency().equals("USD")) {
            long convertedAmount = Math.round(amount.getAmountInCents() * request.getConversionRate());
            amount = Money.ofCents(convertedAmount);
        }
        if (request.isRequiresPin()) {
            System.out.println("PIN verification required for transaction: " + request.getTransactionId());
        }
        System.out.println("Transaction authorized at " + request.getMerchantName()
                + " (" + request.getMerchantCategory() + ") in " + request.getLocation());
        return true;
    }

    public void configureOverdraftProtection(OverdraftProtectionSettings settings) {
        validatePositiveAmount(settings.getOverdraftLimit());
        if (settings.isAutoTransferFromSavings()) {
            Objects.requireNonNull(settings.getSavingsAccount(), "Savings account must not be null for auto-transfer.");
        }
        System.out.println("Overdraft protection configured with limit: " + settings.getOverdraftLimit());
        if (settings.isNotifyOnOverdraft()) {
            System.out.println("Notifications will be sent to: " + settings.getNotificationEmail());
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