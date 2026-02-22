package Esperimento2.Claude.smell14Refactored;

import java.util.Objects;
import utility.Money;

/**
 * Represents a wire transfer operation between two bank accounts.
 * This class encapsulates the data and behavior previously held as temporary fields.
 */
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
    
    /**
     * Executes the wire transfer from source to destination account.
     */
    public void execute() {
        sourceAccount.transferTo(destinationAccount, amount);
    }
    
    public BankAccountRefactored getSourceAccount() {
        return sourceAccount;
    }
    
    public BankAccountRefactored getDestinationAccount() {
        return destinationAccount;
    }
    
    public Money getAmount() {
        return amount;
    }
}
