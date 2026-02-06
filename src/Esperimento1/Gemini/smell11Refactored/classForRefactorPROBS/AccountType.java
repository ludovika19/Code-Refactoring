package Esperimento1.Gemini.smell11Refactored.classForRefactorPROBS;


public enum AccountType {
    CHECKING("Checking"),
    SAVINGS("Savings"),
    BUSINESS("Business");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
