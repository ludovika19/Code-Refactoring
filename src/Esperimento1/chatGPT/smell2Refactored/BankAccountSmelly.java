package Esperimento1.chatGPT.smell2Refactored;

import Esperimento1.chatGPT.smell2Refactored.classesForRefactorDataClumps.*;
import java.util.Objects;
import utility.*;

public class BankAccountSmelly {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

    public BankAccountSmelly(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
    }

    // line 18: (street, city, zipCode) -> Address
    public void updateContactInfo(Address address) {
        System.out.println("Updating address: " + address);
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    // line 28: (firstName, lastName, middleName) -> PersonName
    public void setOwnerDetails(PersonName ownerName) {
        System.out.println("Owner: " + ownerName);
    }

    // line 32: (documentType, documentNumber, issuingCountry) -> DocumentInfo
    public boolean verifyIdentity(DocumentInfo documentInfo) {
        // All fields are non-null by construction; explicit checks retained for semantic equivalence
        return documentInfo.documentType() != null
                && documentInfo.documentNumber() != null
                && documentInfo.issuingCountry() != null;
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.subtract(amount);
    }

    // line 46: merchantName, merchantCategory, location, currency, conversionRate, requiresPin, transactionId -> TransactionContext
    public boolean authorizeTransaction(Money amount, TransactionContext context) {
        validatePositiveAmount(amount);

        if (this.balance.getAmountInCents() < amount.getAmountInCents()) {
            return false;
        }
        if (!"USD".equals(context.currency())) {
            long convertedAmount = Math.round(amount.getAmountInCents() * context.conversionRate());
            amount = Money.ofCents(convertedAmount);
        }
        if (context.requiresPin()) {
            System.out.println("PIN verification required for transaction: " + context.transactionId());
        }
        System.out.println("Transaction authorized at "
                + context.merchantName()
                + " (" + context.merchantCategory() + ") in "
                + context.location());
        return true;
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

    // line 76: (creditScore, yearsOfHistory) -> CreditProfile
    public boolean validateAccountForLoan(Money loanAmount, CreditProfile creditProfile) {
        int creditScore = creditProfile.creditScore();
        int yearsOfHistory = creditProfile.yearsOfHistory();

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
