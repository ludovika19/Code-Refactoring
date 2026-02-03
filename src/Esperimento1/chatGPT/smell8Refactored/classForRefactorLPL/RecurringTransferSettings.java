package Esperimento1.chatGPT.smell8Refactored.classForRefactorLPL;

import utility.Money;
import Esperimento1.chatGPT.smell8Refactored.BankAccountSmelly;
/**
 * Parameters for setting up a recurring transfer.
 */
public class RecurringTransferSettings {

    private final BankAccountSmelly destinationAccount;
    private final Money amount;
    private final String frequency;
    private final String startDate;
    private final String endDate;
    private final boolean notifyOnTransfer;
    private final int maxRetries;

    public RecurringTransferSettings(
            BankAccountSmelly destinationAccount,
            Money amount,
            String frequency,
            String startDate,
            String endDate,
            boolean notifyOnTransfer,
            int maxRetries) {

        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
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