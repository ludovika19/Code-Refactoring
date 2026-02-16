package Esperimento2.Claude.smell5Refactored.utilityRefactored;

import java.time.LocalTime;

public class BankBranch {
    
    // ... existing fields and methods ...
    
    public boolean isOpen(LocalTime now) {
        // existing implementation
    }
    
    public boolean hasStaff() {
        // existing implementation
    }
    
    // MOVED METHOD from BankAccountSmelly
    public String checkOperationalStatus(LocalTime now) {
        if (this.isOpen(now) && this.hasStaff()) {
            return "Branch is fully operational.";
        } else {
            return "Branch is currently closed or understaffed.";
        }
    }
}