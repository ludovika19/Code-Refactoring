package Esperimento2.Claude.smell11Refactored.classForRefactorPROBS;

public enum AccountType {
    CHECKING(1, "Checking"),
    SAVINGS(2, "Savings"),
    BUSINESS(3, "Business");

    private final int code;
    private final String displayName;

    AccountType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static AccountType fromCode(int code) {
        for (AccountType type : AccountType.values()) {
            if (type.code == code) {
                return type;
            }
        }
        return BUSINESS; // default as per original logic
    }
}