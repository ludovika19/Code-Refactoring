package Esperimento2.Gemini.smell5Refactored.utilityRefactored;

import java.time.LocalTime;

public class BankBranch {
    // Assuming some internal state for demonstration
    private boolean hasStaff;
    private LocalTime openingTime;
    private LocalTime closingTime;

    public BankBranch(LocalTime openingTime, LocalTime closingTime, boolean hasStaff) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.hasStaff = hasStaff;
    }

    public boolean isOpen(LocalTime time) {
        return !time.isBefore(openingTime) && time.isBefore(closingTime);
    }

    public boolean hasStaff() {
        return hasStaff;
    }

    /**
     * New method moved from BankAccountSmelly.
     * This logic belongs here as it only uses BankBranch's data.
     */
    public String getOperationalStatus(LocalTime now) {
        if (isOpen(now) && hasStaff()) {
            return "Branch is fully operational.";
        } else {
            return "Branch is currently closed or understaffed.";
        }
    }
}