package Esperimento2.chatGPT.smell11Refactored.classForRefactorPROBS;


public enum TransactionType {
    WIRE,
    ATM,
    TRANSFER,
    CHECK,
    OTHER;

    public static TransactionType fromString(String value) {
        if (value == null) {
            return OTHER;
        }
        switch (value) {
            case "WIRE":
                return WIRE;
            case "ATM":
                return ATM;
            case "TRANSFER":
                return TRANSFER;
            case "CHECK":
                return CHECK;
            default:
                return OTHER;
        }
    }
}
