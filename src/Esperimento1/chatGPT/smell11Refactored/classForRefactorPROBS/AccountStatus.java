package Esperimento1.chatGPT.smell11Refactored.classForRefactorPROBS;

public enum AccountStatus {
    ACTIVE,
    FROZEN,
    CLOSED;

    public boolean isClosed() {
        return this == CLOSED;
    }
}
