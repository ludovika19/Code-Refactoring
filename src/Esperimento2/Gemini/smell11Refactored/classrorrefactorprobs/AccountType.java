package Esperimento2.Gemini.smell11Refactored.classrorrefactorprobs;


public enum AccountType {
    CHECKING(1, "Checking"),
    SAVINGS(2, "Savings"),
    BUSINESS(3, "Business");

    private final int code;
    private final String description;

    AccountType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static AccountType fromCode(int code) {
        for (AccountType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        // Defaulting to Business as per original logic
        return BUSINESS;
    }
}