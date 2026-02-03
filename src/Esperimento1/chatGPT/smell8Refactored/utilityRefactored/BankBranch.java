package Esperimento1.chatGPT.smell5Refactored.utilityRefactored;


import java.time.LocalTime;

public class BankBranch {

    // Example fields – structure can vary in your real project.
    private final String name;
    private final LocalTime openingTime;
    private final LocalTime closingTime;
    private final boolean staffed;

    public BankBranch(String name, LocalTime openingTime, LocalTime closingTime, boolean staffed) {
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.staffed = staffed;
    }

    public boolean isOpen(LocalTime now) {
        return !now.isBefore(openingTime) && !now.isAfter(closingTime);
    }

    public boolean hasStaff() {
        return staffed;
    }

    // Extracted + moved behavior to avoid Feature Envy in BankAccountSmelly
    public String getOperationalStatus(LocalTime now) {
        if (this.isOpen(now) && this.hasStaff()) {
            return "Branch is fully operational.";
        } else {
            return "Branch is currently closed or understaffed.";
        }
    }

    public String getName() {
        return name;
    }
}
