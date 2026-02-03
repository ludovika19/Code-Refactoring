package Esperimento1.Gemini.smell8Refactored.classForRefactorLPL;


import utility.Money;
import java.util.Objects;

import Esperimento1.Gemini.smell8Refactored.BankAccount;

// Parameter Object for setting up a recurring transfer
public class RecurringTransferConfig {
    private final BankAccount destinationAccount;
    private final Money amount;
    private final String frequency;
    private final String startDate;
    private final String endDate;
    private final boolean notifyOnTransfer;
    private final int maxRetries;

    public RecurringTransferConfig(BankAccount destinationAccount, Money amount, String frequency, String startDate, String endDate, boolean notifyOnTransfer, int maxRetries) {
        this.destinationAccount = Objects.requireNonNull(destinationAccount, "Destination account must not be null.");
        this.amount = Objects.requireNonNull(amount, "Amount must not be null.");
        this.frequency = Objects.requireNonNull(frequency, "Frequency must not be null.");
        this.startDate = Objects.requireNonNull(startDate, "Start date must not be null.");
        this.endDate = Objects.requireNonNull(endDate, "End date must not be null.");
        this.notifyOnTransfer = notifyOnTransfer;
        this.maxRetries = maxRetries;
    }

    // Getters for all fields
    public BankAccount getDestinationAccount() { return destinationAccount; }
    public Money getAmount() { return amount; }
    public String getFrequency() { return frequency; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public boolean shouldNotifyOnTransfer() { return notifyOnTransfer; }
    public int getMaxRetries() { return maxRetries; }
}
