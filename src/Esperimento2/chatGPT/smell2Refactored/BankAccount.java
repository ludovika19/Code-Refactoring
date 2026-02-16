package Esperimento2.chatGPT.smell2Refactored;

import java.util.Objects;

import Esperimento2.chatGPT.smell2Refactored.classForRefactorDC.*;
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

    // Data Clump (street, city, zipCode) replaced with Address
    public void updateContactInfo(Address address) {
        System.out.println("Updating address: " + address);
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    // Data Clump (firstName, lastName, middleName) replaced with PersonName
    public void setOwnerDetails(PersonName name) {
        System.out.println("Owner: " + name);
    }

    // Data Clump (documentType, documentNumber, issuingCountry) replaced with IdentityDocument
    public boolean verifyIdentity(IdentityDocument document) {
        return document.getDocumentType() != null
                && document.getDocumentNumber() != null
                && document.getIssuingCountry() != null;
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.subtract(amount);
    }

    // Data Clump of transaction-related parameters replaced with TransactionDetails
    public boolean authorizeTransaction(Money amount, TransactionDetails details) {
        validatePositiveAmount(amount);

        if (this.balance.getAmountInCents() < amount.getAmountInCents()) {
            return false;
        }

        Money effectiveAmount = amount;
        if (!"USD".equals(details.getCurrency())) {
            long convertedAmount = Math.round(amount.getAmountInCents() * details.getConversionRate());
            effectiveAmount = Money.ofCents(convertedAmount);
        }

        if (details.isRequiresPin()) {
            System.out.println("PIN verification required for transaction: " + details.getTransactionId());
        }

        System.out.println(
                "Transaction authorized at "
                        + details.getMerchantName()
                        + " (" + details.getMerchantCategory() + ") in "
                        + details.getLocation()
        );

        // NOTE: The original code compared against the (possibly) converted amount,
        // but did not actually deduct that effective amount, only the original amount.
        // To preserve behavior exactly, we still deduct the original 'amount'.
        this.balance = this.balance.subtract(amount);

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