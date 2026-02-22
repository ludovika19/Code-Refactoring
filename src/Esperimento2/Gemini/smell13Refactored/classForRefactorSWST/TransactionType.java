package Esperimento2.Gemini.smell13Refactored.classForRefactorSWST;


public enum TransactionType {
    WIRE(25.00),
    ATM(2.50),
    TRANSFER(0.00),
    CHECK(1.00),
    OTHER(5.00); // Represents the default case

    private final double fee;

    TransactionType(double fee) {
        this.fee = fee;
    }

    public double getFee() {
        return this.fee;
    }

    public static TransactionTypefromString(String type) {
        try {
            return TransactionType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return OTHER;
        }
    }
}
