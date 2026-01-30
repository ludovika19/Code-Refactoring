package Esperimento1.chatGPT.smell1Refactored;

import java.util.Objects;
import utility.*;

public class BankAccountSmelly {

    private static final String CLOSED_STATUS = "CLOSED";

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

    private boolean isClosed() {
        return CLOSED_STATUS.equals(this.accountStatus);
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);

        if (!isClosed()) {
            this.balance = this.balance.add(amount);
        }
    }

    public boolean validateAccountForLoan(Money loanAmount, int creditScore, int yearsOfHistory) {
        if (!isCreditScoreInValidRange(creditScore)) {
            return false;
        }
        if (!hasNonNegativeHistory(yearsOfHistory)) {
            return false;
        }

        Money minimumBalance = Money.ofCents(loanAmount.getAmountInCents() / 10);
        if (!hasMinimumBalance(minimumBalance)) {
            return false;
        }

        if (isInsufficientHistoryForCreditScore(creditScore, yearsOfHistory)) {
            return false;
        }

        if (isHighAmountForLowCreditScore(loanAmount, creditScore)) {
            return false;
        }

        return true;
    }

    private boolean isCreditScoreInValidRange(int creditScore) {
        return creditScore >= 300 && creditScore <= 850;
    }

    private boolean hasNonNegativeHistory(int yearsOfHistory) {
        return yearsOfHistory >= 0;
    }

    private boolean hasMinimumBalance(Money minimumBalance) {
        return this.balance.getAmountInCents() >= minimumBalance.getAmountInCents();
    }

    private boolean isInsufficientHistoryForCreditScore(int creditScore, int yearsOfHistory) {
        if (creditScore < 600 && yearsOfHistory < 2) {
            return true;
        }
        return creditScore >= 600 && creditScore < 700 && yearsOfHistory < 1;
    }

    private boolean isHighAmountForLowCreditScore(Money loanAmount, int creditScore) {
        long loanAmountInCents = loanAmount.getAmountInCents();
        return loanAmountInCents > 100_000_000L && creditScore < 750;
    }

    public boolean authorizeTransaction(Money amount,
                                        String merchantName,
                                        String merchantCategory,
                                        String location,
                                        String currency,
                                        double conversionRate,
                                        boolean requiresPin,
                                        String transactionId) {
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

    private boolean hasSufficientBalanceFor(Money amount) {
        return this.balance.getAmountInCents() >= amount.getAmountInCents();
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
