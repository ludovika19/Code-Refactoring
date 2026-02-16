package Esperimento2.Gemini.smell8Refactored.classForRefactorLPL;

import java.util.Objects;

public class RecurringTransferConfig {
    private final String frequency;
    private final String startDate;
    private final String endDate;
    private final boolean notifyOnTransfer;
    private final int maxRetries;

    public RecurringTransferConfig(String frequency, String startDate, String endDate, boolean notifyOnTransfer, int maxRetries) {
        this.frequency = Objects.requireNonNull(frequency, "Frequency must not be null.");
        this.startDate = Objects.requireNonNull(startDate, "Start date must not be null.");
        this.endDate = Objects.requireNonNull(endDate, "End date must not be null.");
        this.notifyOnTransfer = notifyOnTransfer;
        this.maxRetries = maxRetries;
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