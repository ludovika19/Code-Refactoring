package Esperimento2.chatGPT.smell8Refactored.classForRefactorLPL;

import java.util.Objects;
import utility.Money;

public class RecurringTransferSettings {
    private final BankAccountSmelly destinationAccount;
    private final Money amount;
    private final String frequency;
    private final String startDate;
    private final String endDate;
    private final boolean notifyOnTransfer;
    private final int maxRetries;

    public RecurringTransferSettings(BankAccountSmelly destinationAccount,
                                     Money amount,
                                     String frequency,
                                     String startDate,
                                     String endDate,
                                     boolean notifyOnTransfer,
                                     int maxRetries) {
        this.destinationAccount = Objects.requireNonNull(destinationAccount, "Destination account must not be null.");
        this.amount = Objects.requireNonNull(amount, "Amount must not be null.");
        this.frequency = Objects.requireNonNull(frequency, "Frequency must not be null.");
        this.startDate = Objects.requireNonNull(startDate, "Start date must not be null.");
        this.endDate = Objects.requireNonNull(endDate, "End date must not be null.");
        this.notifyOnTransfer = notifyOnTransfer;
        this.maxRetries = maxRetries;
    }

    public BankAccountSmelly getDestinationAccount() {
        return destinationAccount;
    }

    public Money getAmount() {
        return amount;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public boolean isNotifyOnTransfer() {
        return notifyOnTransfer;
    }

    public int getMaxRetries() {
        return maxRetries;
    }
}