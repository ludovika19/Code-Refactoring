package Esperimento1.chatGPT.smell8Refactored;


import java.util.Objects;
import Esperimento1.chatGPT.smell8Refactored.classForRefactorLPL.*;
import utility.*;


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

    /**
     * Refactored from:
     * setupRecurringTransfer(BankAccountSmelly destinationAccount,
     *                        Money amount,
     *                        String frequency,
     *                        String startDate,
     *                        String endDate,
     *                        boolean notifyOnTransfer,
     *                        int maxRetries)
     *
     * Uses Introduce Parameter Object (RecurringTransferSettings).
     */
    public void setupRecurringTransfer(RecurringTransferSettings settings) {
        Objects.requireNonNull(settings.getDestinationAccount(), "Destination account must not be null.");
        validatePositiveAmount(settings.getAmount());

        String frequency = settings.getFrequency();
        String startDate = settings.getStartDate();
        String endDate = settings.getEndDate();

        if (frequency.equals("DAILY") || frequency.equals("WEEKLY") || frequency.equals("MONTHLY")) {
            System.out.println("Recurring transfer set up from " + startDate + " to " + endDate);
            if (settings.isNotifyOnTransfer()) {
                System.out.println("Notifications enabled with max retries: " + settings.getMaxRetries());
            }
        }
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    /**
     * Refactored from:
     * authorizeTransaction(Money amount,
     *                      String merchantName,
     *                      String merchantCategory,
     *                      String location,
     *                      String currency,
     *                      double conversionRate,
     *                      boolean requiresPin,
     *                      String transactionId)
     *
     * Uses Introduce Parameter Object (TransactionRequest) and
     * Introduce Parameter Object (MerchantInfo) for the merchant-related data.
     */
    public boolean authorizeTransaction(TransactionRequest request) {
        Money amount = request.getAmount();
        validatePositiveAmount(amount);

        if (this.balance.getAmountInCents() < amount.getAmountInCents()) {
            return false;
        }

        String currency = request.getCurrency();
        double conversionRate = request.getConversionRate();

        if (!"USD".equals(currency)) {
            long convertedAmount = Math.round(amount.getAmountInCents() * conversionRate);
            amount = Money.ofCents(convertedAmount);
        }

        if (request.isRequiresPin()) {
            System.out.println("PIN verification required for transaction: " + request.getTransactionId());
        }

        MerchantInfo merchantInfo = request.getMerchantInfo();
        System.out.println("Transaction authorized at "
                + merchantInfo.getMerchantName()
                + " (" + merchantInfo.getMerchantCategory() + ") in "
                + merchantInfo.getLocation());

        return true;
    }

    /**
     * Refactored from:
     * configureOverdraftProtection(Money overdraftLimit,
     *                              boolean autoTransferFromSavings,
     *                              BankAccountSmelly savingsAccount,
     *                              boolean notifyOnOverdraft,
     *                              String notificationEmail)
     *
     * Uses Introduce Parameter Object (OverdraftProtectionSettings).
     */
    public void configureOverdraftProtection(OverdraftProtectionSettings settings) {
        validatePositiveAmount(settings.getOverdraftLimit());

        if (settings.isAutoTransferFromSavings()) {
            Objects.requireNonNull(settings.getSavingsAccount(),
                    "Savings account must not be null for auto-transfer.");
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
