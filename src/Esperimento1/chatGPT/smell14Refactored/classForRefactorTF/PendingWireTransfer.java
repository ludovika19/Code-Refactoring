package Esperimento1.chatGPT.smell14Refactored.classForRefactorTF;

import utility.Money;
import Esperimento1.chatGPT.smell14Refactored.BankAccountSmelly;

public final class PendingWireTransfer {

    private final BankAccountSmelly destinationAccount;
    private final Money amount;

    PendingWireTransfer(BankAccountSmelly destinationAccount, Money amount) {
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    BankAccountSmelly getDestinationAccount() {
        return destinationAccount;
    }

    Money getAmount() {
        return amount;
    }
}