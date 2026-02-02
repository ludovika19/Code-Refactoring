package Esperimento1.ClaudeSonet.smelly1Refactored;


import java.util.Objects;
import utility.*;

public class BankAccountRefactored {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;
    private String accountStatus;

    public BankAccountRefactored(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    private boolean isAccountClosed() {
        return this.accountStatus.equals("CLOSED");
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        
        if (!isAccountClosed()) {
            this.balance = this.balance.add(amount);
        }
    }

    private boolean isLoanAmountTooHighForCreditScore(Money loanAmount, int creditScore) {
        return loanAmount.getAmountInCents() > 100000000 && creditScore < 750;
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

        if (isLoanAmountTooHighForCreditScore(loanAmount, creditScore)) {
            return false;
        }
        return true;
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
        this.balance = this.balance.subtract(amount);
    }

    public void transferTo(BankAccountRefactored otherAccount, Money amount) {
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
