package Esperimento1.Gemini.smell11Refactored.classForRefactorPROBS;


public enum TransactionType {
    WIRE(25.00),
    ATM(2.50),
    TRANSFER(0.00),
    CHECK(1.00),
    OTHER(5.00);

    private final double fee;

    TransactionType(double fee) {
        this.fee = fee;
    }

    public double getFee() {
        return fee;
    }
}