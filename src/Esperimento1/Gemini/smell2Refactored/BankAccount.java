package Esperimento1.Gemini.smell2Refactored;

import java.util.Objects;

import Esperimento1.Gemini.smell2Refactored.classesForRefactorDataClumps.*;
import utility.*;

public class BankAccount {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

    public BankAccount(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
    }

    public void updateContactInfo(Address address) {
        System.out.println("Updating address: " + address);
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    public void setOwnerDetails(FullName fullName) {
        System.out.println("Owner: " + fullName);
    }

    public boolean verifyIdentity(IdentityDocument document) {
        return document.isValid();
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.subtract(amount);
    }

    public boolean authorizeTransaction(TransactionDetails transaction) {
        validatePositiveAmount(transaction.getAmount());

        if (this.balance.getAmountInCents() < transaction.getAmount().getAmountInCents()) {
            return false;
        }

        Money transactionAmount = transaction.getAmount();
        if (!transaction.getCurrency().equals("USD")) {
            long convertedAmount = Math.round(transaction.getAmount().getAmountInCents() * transaction.getConversionRate());
            transactionAmount = Money.ofCents(convertedAmount);
        }

        if (transaction.isPinRequired()) {
            System.out.println("PIN verification required for transaction: " + transaction.getTransactionId());
        }
        System.out.println("Transaction authorized at " + transaction.getMerchantName() + " (" + transaction.getMerchantCategory() + ") in " + transaction.getLocation());
        return true;
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

    public boolean validateAccountForLoan(LoanDetails loanDetails) {
        int creditScore = loanDetails.getCreditScore();
        int yearsOfHistory = loanDetails.getYearsOfHistory();
        Money loanAmount = loanDetails.getLoanAmount();

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

        if (loanAmount.getAmountInCents() > 100000000 && creditScore < 750) {
            return false;
        }
        return true;
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