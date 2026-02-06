package Esperimento1.chatGPT.smell11Refactored.classForRefactorPROBS;


public enum AccountType {
    CHECKING("Checking", "0.00%", "$0.00", "No"),
    SAVINGS("Savings", "0.00%", "$0.00", "No"),
    BUSINESS("Business", "0.00%", "$0.00", "No");

    private final String displayName;
    private final String interestRateDescription;
    private final String monthlyFeeDescription;
    private final String overdraftProtectionDescription;

    AccountType(String displayName,
                String interestRateDescription,
                String monthlyFeeDescription,
                String overdraftProtectionDescription) {
        this.displayName = displayName;
        this.interestRateDescription = interestRateDescription;
        this.monthlyFeeDescription = monthlyFeeDescription;
        this.overdraftProtectionDescription = overdraftProtectionDescription;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getInterestRateDescription() {
        return interestRateDescription;
    }

    public String getMonthlyFeeDescription() {
        return monthlyFeeDescription;
    }

    public String getOverdraftProtectionDescription() {
        return overdraftProtectionDescription;
    }
}