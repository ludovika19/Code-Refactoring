package Esperimento2.Gemini.smell11Refactored.classrorrefactorprobs;


public enum AccountStatus {
    ACTIVE,
    FROZEN,
    CLOSED;

    public boolean isClosed() {
        return this == CLOSED;
    }

    public static AccountStatus fromString(String status) {
        for (AccountStatus s : values()) {
            if (s.name().equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invalid account status: " + status);
    }
}