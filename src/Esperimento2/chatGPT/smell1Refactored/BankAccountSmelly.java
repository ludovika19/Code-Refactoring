package Esperimento2.chatGPT.smell1Refactored;

import java.util.Objects;
import utility.*;

public class BankAccountSmelly {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;
    private String accountStatus;

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

    // Extract Method to make the deposit condition self-explanatory
    private boolean canAcceptDeposits() {
        return !Objects.equals(this.accountStatus, "CLOSED");
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);

        if (canAcceptDeposits()) {
            this.balance = this.balance.add(amount);
        }
    }

    // Extract Method / Decompose Conditional for clarity of loan validation rules
    private boolean hasValidCreditScoreRange(int creditScore) {
        return creditScore >= 300 && creditScore <= 850;
    }

    private boolean hasValidHistoryYears(int yearsOfHistory) {
        return yearsOfHistory >= 0;
    }

    private boolean hasRequiredMinimumBalanceForLoan(Money loanAmount) {
        Money minimumBalance = Money.ofCents(loanAmount.getAmountInCents() / 10);
        return this.balance.getAmountInCents() >= minimumBalance.getAmountInCents();
    }

    private boolean hasSufficientCreditScoreAndHistory(int creditScore, int yearsOfHistory) {
        if (creditScore < 600 && yearsOfHistory < 2) {
            return false;
        }
        if (creditScore >= 600 && creditScore < 700 && yearsOfHistory < 1) {
            return false;
        }
        return true;
    }

    // Extract Method to encode the rule “reject high loan amounts for low credit scores”
    private boolean hasHighLoanAmountForLowCreditScore(Money loanAmount, int creditScore) {
        return loanAmount.getAmountInCents() > 100000000 && creditScore < 750;
    }

    public boolean validateAccountForLoan(Money loanAmount, int creditScore, int yearsOfHistory) {
        if (!hasValidCreditScoreRange(creditScore)) {
            return false;
        }
        if (!hasValidHistoryYears(yearsOfHistory)) {
            return false;
        }
        if (!hasRequiredMinimumBalanceForLoan(loanAmount)) {
            return false;
        }
        if (!hasSufficientCreditScoreAndHistory(creditScore, yearsOfHistory)) {
            return false;
        }
        if (hasHighLoanAmountForLowCreditScore(loanAmount, creditScore)) {
            return false;
        }
        return true;
    }

    // Extract Method to make “ensure sufficient balance before authorizing” explicit
    private boolean hasSufficientBalanceFor(Money amount) {
        return this.balance.getAmountInCents() >= amount.getAmountInCents();
    }

    public boolean authorizeTransaction(
            Money amount,
            String merchantName,
            String merchantCategory,
            String location,
            String currency,
            double conversionRate,
            boolean requiresPin,
            String transactionId
    ) {
        validatePositiveAmount(amount);

        if (!hasSufficientBalanceFor(amount)) {
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