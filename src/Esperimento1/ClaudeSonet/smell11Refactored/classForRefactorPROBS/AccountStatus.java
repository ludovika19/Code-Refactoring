package Esperimento1.ClaudeSonet.smell11Refactored.classForRefactorPROBS;


public enum AccountStatus {
    ACTIVE,
    FROZEN,
    CLOSED;

    public boolean allowsDeposits() {
        return this != CLOSED;
    }
}
