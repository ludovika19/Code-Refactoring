package Esperimento2.chatGPT.smell14Refactored;

import java.util.Objects;
import utility.*;

public class WireTransfer {

    private final BankAccountRefactored sourceAccount;
    private final BankAccountRefactored destinationAccount;
    private final Money amount;

    public WireTransfer(BankAccountRefactored sourceAccount,
                        BankAccountRefactored destinationAccount,
                        Money amount) {
        this.sourceAccount = Objects.requireNonNull(sourceAccount, "Source account must not be null.");
        this.destinationAccount = Objects.requireNonNull(destinationAccount, "Destination account must not be null.");
        this.amount = Objects.requireNonNull(amount, "Amount must not be null.");
    }

    public void execute() {
        sourceAccount.transferTo(destinationAccount, amount);
    }
}
