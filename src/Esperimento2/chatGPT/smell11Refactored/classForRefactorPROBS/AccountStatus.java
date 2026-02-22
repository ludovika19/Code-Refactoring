package Esperimento2.chatGPT.smell11Refactored.classForRefactorPROBS;


public enum AccountStatus {
    ACTIVE,
    FROZEN,
    CLOSED;

    public boolean isClosed() {
        return this == CLOSED;
    }

    public static AccountStatus fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Account status must not be null.");
        }
        switch (value) {
            case "ACTIVE":
                return ACTIVE;
            case "FROZEN":
                return FROZEN;
            case "CLOSED":
                return CLOSED;
            default:
                throw new IllegalArgumentException("Unknown account status: " + value);
        }
    }
}
