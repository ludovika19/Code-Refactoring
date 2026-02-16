package Esperimento1.chatGPT.smell14Refactored;

import java.util.Objects;
import utility.*;
import Esperimento1.chatGPT.smell14Refactored.classForRefactorTF.PendingWireTransfer;

public class BankAccountSmelly {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

    // Replaces the two temporary fields:
    // - pendingTransferAmount
    // - temporaryDestinationAccount
    private PendingWireTransfer pendingWireTransfer;

    public BankAccountSmelly(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    public void initiateWireTransfer(BankAccountSmelly destination, Money amount) {
        // Keep the original semantics: set up a pending transfer without executing it yet.
        this.pendingWireTransfer = new PendingWireTransfer(destination, amount);
    }

    public void completeWireTransfer() {
        if (this.pendingWireTransfer != null) {
            this.transferTo(
                this.pendingWireTransfer.getDestinationAccount(),
                this.pendingWireTransfer.getAmount()
            );
            this.pendingWireTransfer = null;
        }
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.subtract(amount);
    }

    public void transferTo(BankAccountSmelly otherAccount, Money amount) {
        Objects.requireNonNull(otherAccount, "Destination account must not be null.");

        this.withdraw(amount);
        otherAccount.deposit(amount);
    }

    public Money withdrawAll() {
        Money amount = this.balance;
        this.balance = Money.ofCents(0);
        return amount;
    }

    public Money getBalance() {
        return this.balance;
    }

    public AccountHolder getAccountHolder() {
        return this.accountHolder;
    }

    public AccountID getAccountId() {
        return this.accountId;
    }

    @Override
    public String toString() {
        return "BankAccount[accountId=" + accountId +
               ", accountHolder=" + accountHolder +
               ", balance=" + balance + " cents]";
    }
}