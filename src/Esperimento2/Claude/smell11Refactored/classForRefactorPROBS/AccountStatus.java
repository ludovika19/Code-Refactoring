package Esperimento2.Claude.smell11Refactored.classForRefactorPROBS;


public enum AccountStatus {
    ACTIVE,
    FROZEN,
    CLOSED;

    public boolean isClosed() {
        return this == CLOSED;
    }

    public static AccountStatus fromString(String status) {
        try {
            return AccountStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid account status: " + status);
        }
    }
}
