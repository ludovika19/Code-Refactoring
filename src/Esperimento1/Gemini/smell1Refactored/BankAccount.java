package Esperimento1.Gemini.smell1Refactored;

import java.util.Objects;
import utility.*;

public class BankAccount {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;
    private String accountStatus;

    public BankAccount(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
        // Assuming a default status upon creation
        this.accountStatus = "OPEN"; 
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    private boolean isAccountOpen() {
        return !"CLOSED".equals(this.accountStatus);
    }
    
    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        
        if (isAccountOpen()) {
            this.balance = this.balance.add(amount);
        }
    }

    private boolean isLoanAmountAcceptableForCreditScore(Money loanAmount, int creditScore) {
        if (loanAmount.getAmountInCents() > 100000000 && creditScore < 750) {
            return false;
        }
        return true;
    }

    public boolean validateAccountForLoan(Money loanAmount, int creditScore, int yearsOfHistory) {
        if (creditScore < 300 || creditScore > 850) {
            return false;
        }
        if (yearsOfHistory < 0) {
            return false;
        }
        Money minimumBalance = Money.ofCents(loanAmount.getAmountInCents() / 10);
        if (this.balance.getAmountInCents() < minimumBalance.getAmountInCents()) {
            return false;
        }
        if (creditScore < 600 && yearsOfHistory < 2) {
            return false;
        }
        if (creditScore >= 600 && creditScore < 700 && yearsOfHistory < 1) {
            return false;
        }

        return isLoanAmountAcceptableForCreditScore(loanAmount, creditScore);
    }

    private boolean hasSufficientBalance(Money amount) {
        return this.balance.getAmountInCents() >= amount.getAmountInCents();
    }

    public boolean authorizeTransaction(Money amount, String merchantName, String merchantCategory, String location, String currency, double conversionRate, boolean requiresPin, String transactionId) {
        validatePositiveAmount(amount);
        
        if (!hasSufficientBalance(amount)) {
            return false;
        }

        if (!currency.equals("USD")) {
            long convertedAmount = Math.round(amount.getAmountInCents() * conversionRate);
            amount = Money.ofCents(convertedAmount);
        }
        if (requiresPin) {
            System.out.println("PIN verification required for transaction: " + transactionId);
        }
        System.out.println("Transaction authorized at " + merchantName + " (" + merchantCategory + ") in " + location);
        return true;
    }

    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
        if (!hasSufficientBalance(amount)) {
            throw new IllegalStateException("Insufficient funds for withdrawal.");
        }
        this.balance = this.balance.subtract(amount);
    }

    public void transferTo(BankAccount otherAccount, Money amount) {
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
