package Esperimento1.chatGPT.smell11Refactored.classForRefactorPROBS;


import utility.Money;

public enum TransactionType {
    WIRE(25_00),
    ATM(2_50),
    TRANSFER(0_00),
    CHECK(1_00),
    OTHER(5_00);

    private final Money fee;

    TransactionType(int feeInCents) {
        this.fee = Money.ofCents(feeInCents);
    }

    public Money getFee() {
        return fee;
    }
}
