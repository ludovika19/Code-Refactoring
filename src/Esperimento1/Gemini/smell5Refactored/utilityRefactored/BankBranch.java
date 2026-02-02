package Esperimento1.Gemini.smell5Refactored.utilityRefactored;

import java.time.LocalTime;

public class BankBranch {

    // Assuming these methods exist based on the original code
    public boolean isOpen(LocalTime time) {
        // Implementation details are assumed
        return true;
    }

    public boolean hasStaff() {
        // Implementation details are assumed
        return true;
    }

    /**
     * This method was moved from BankAccountSmelly as it was exhibiting Feature Envy.
     * Its logic is entirely dependent on the state of the BankBranch.
     */
    public String getOperationalStatus(java.time.LocalTime now) {
        if (this.isOpen(now) && this.hasStaff()) {
            return "Branch is fully operational.";
        } else {
            return "Branch is currently closed or understaffed.";
        }
    }
}