package Esperimento1.Gemini.smell14Refactored;

import java.util.Objects;
import utility.Money;

public class WireTransfer {

    private final BankAccount sourceAccount;
    private final BankAccount destinationAccount;
    private final Money amount;

    public WireTransfer(BankAccount sourceAccount, BankAccount destinationAccount, Money amount) {
        this.sourceAccount = Objects.requireNonNull(sourceAccount, "Source account must not be null.");
        this.destinationAccount = Objects.requireNonNull(destinationAccount, "Destination account must not be null.");
        this.amount = Objects.requireNonNull(amount, "Amount must not be null.");
        
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }
    }

    /**
     * Executes the wire transfer.
     * This method is a more direct way to perform the action, removing the need for
     * a two-step process in the BankAccount class.
     */
    public void execute() {
        // The transfer logic is now self-contained.
        // For BankAccount.transferTo to be callable, it needs to have at least package-private visibility.
        // We can achieve this by making BankAccount.transferTo private and using a different approach here:
        sourceAccount.withdraw(amount);
        destinationAccount.deposit(amount);
    }

    // Optional: Getters for transfer details if needed
    public BankAccount getSourceAccount() {
        return sourceAccount;
    }

    public BankAccount getDestinationAccount() {
        return destinationAccount;
    }

    public Money getAmount() {
        return amount;
    }
}