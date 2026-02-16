package Esperimento2.chatGPT.smell5Refactored.utilityRefactored;


import java.time.LocalTime;

public class BankBranch {

    // Other fields and methods omitted, as they are not shown in the original snippet.

    public boolean isOpen(LocalTime time) {
        // existing implementation
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean hasStaff() {
        // existing implementation
        throw new UnsupportedOperationException("Not implemented");
    }

    // === Method moved from BankAccountSmelly (Feature Envy fix) ===

    /**
     * Returns a human-readable description of the branch's operational status
     * at the given time.
     */
    public String getOperationalStatus(LocalTime now) {
        if (this.isOpen(now) && this.hasStaff()) {
            return "Branch is fully operational.";
        } else {
            return "Branch is currently closed or understaffed.";
        }
    }
}
