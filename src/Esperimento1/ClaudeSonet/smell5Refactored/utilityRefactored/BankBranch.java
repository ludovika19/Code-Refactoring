package Esperimento1.ClaudeSonet.smell5Refactored.utilityRefactored;

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

   // Add this method to the BankBranch class to eliminate Feature Envy

public boolean isFullyOperational(java.time.LocalTime now) {
    return this.isOpen(now) && this.hasStaff();
}
}