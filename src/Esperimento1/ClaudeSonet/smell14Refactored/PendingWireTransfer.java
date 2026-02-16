package Esperimento1.ClaudeSonet.smell14Refactored;


import java.util.Objects;
import utility.Money;

public class PendingWireTransfer {
    
    private final BankAccount destinationAccount;
    private final Money amount;

    public PendingWireTransfer(BankAccount destinationAccount, Money amount) {
        this.destinationAccount = Objects.requireNonNull(destinationAccount, "Destination account must not be null.");
        this.amount = Objects.requireNonNull(amount, "Amount must not be null.");
    }

    public void execute(BankAccount sourceAccount) {
        sourceAccount.transferTo(this.destinationAccount, this.amount);
    }

    public BankAccount getDestinationAccount() {
        return this.destinationAccount;
    }

    public Money getAmount() {
        return this.amount;
    }
}
