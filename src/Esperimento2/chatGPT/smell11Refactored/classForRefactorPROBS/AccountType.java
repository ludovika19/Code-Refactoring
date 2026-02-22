package Esperimento2.chatGPT.smell11Refactored.classForRefactorPROBS;


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

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static AccountType fromCode(int code) {
        switch (code) {
            case 1:
                return CHECKING;
            case 2:
                return SAVINGS;
            default:
                return BUSINESS;
        }
    }
}
