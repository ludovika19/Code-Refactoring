package Esperimento1.ClaudeSonet.smell8Refactored.classForRefactorLPL;


public class RecurringTransferConfig {
    public final String frequency;
    public final String startDate;
    public final String endDate;
    public final boolean notifyOnTransfer;
    public final int maxRetries;

    public RecurringTransferConfig(String frequency, String startDate, String endDate, boolean notifyOnTransfer, int maxRetries) {
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notifyOnTransfer = notifyOnTransfer;
        this.maxRetries = maxRetries;
    }
}
