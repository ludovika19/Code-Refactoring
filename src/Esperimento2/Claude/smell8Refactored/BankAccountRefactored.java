package Esperimento2.Claude.smell8Refactored;

import java.util.Objects;
import utility.*;

public class BankAccountRefactored {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

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

    public void setupRecurringTransfer(RecurringTransferConfig config) {
        validatePositiveAmount(config.getAmount());
        if (config.getFrequency().equals("DAILY") || config.getFrequency().equals("WEEKLY") || config.getFrequency().equals("MONTHLY")) {
            System.out.println("Recurring transfer set up from " + config.getStartDate() + " to " + config.getEndDate());
            if (config.isNotifyOnTransfer()) {
                System.out.println("Notifications enabled with max retries: " + config.getMaxRetries());
            }
        }
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public boolean authorizeTransaction(TransactionAuthorizationRequest request) {
        validatePositiveAmount(request.getAmount());
        if (this.balance.getAmountInCents() < request.getAmount().getAmountInCents()) {
            return false;
        }
        Money amount = request.getAmount();
        if (!request.getCurrency().equals("USD")) {
            long convertedAmount = Math.round(request.getAmount().getAmountInCents() * request.getConversionRate());
            amount = Money.ofCents(convertedAmount);
        }
        if (request.isRequiresPin()) {
            System.out.println("PIN verification required for transaction: " + request.getTransactionId());
        }
        System.out.println("Transaction authorized at " + request.getMerchantName() + " (" + request.getMerchantCategory() + ") in " + request.getLocation());
        return true;
    }

    public void configureOverdraftProtection(OverdraftProtectionConfig config) {
        validatePositiveAmount(config.getOverdraftLimit());
        if (config.isAutoTransferFromSavings()) {
            Objects.requireNonNull(config.getSavingsAccount(), "Savings account must not be null for auto-transfer.");
        }
        System.out.println("Overdraft protection configured with limit: " + config.getOverdraftLimit());
        if (config.isNotifyOnOverdraft()) {
            System.out.println("Notifications will be sent to: " + config.getNotificationEmail());
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
