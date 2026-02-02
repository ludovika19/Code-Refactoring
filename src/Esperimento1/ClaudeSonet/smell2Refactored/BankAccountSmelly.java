package Esperimento1.ClaudeSonet.smell2Refactored;

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

    public void updateContactInfo(Address address) {
        Objects.requireNonNull(address, "Address must not be null.");
        System.out.println("Updating address: " + address.toString());
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    public void setOwnerDetails(PersonName personName) {
        Objects.requireNonNull(personName, "Person name must not be null.");
        System.out.println("Owner: " + personName.getFullName());
    }

    public boolean verifyIdentity(IdentityDocument identityDocument) {
        Objects.requireNonNull(identityDocument, "Identity document must not be null.");
        return identityDocument.isValid();
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.subtract(amount);
    }

    public boolean authorizeTransaction(Money amount, MerchantInfo merchantInfo, CurrencyConversion currencyConversion, boolean requiresPin, String transactionId) {
        Objects.requireNonNull(merchantInfo, "Merchant info must not be null.");
        Objects.requireNonNull(currencyConversion, "Currency conversion must not be null.");
        Objects.requireNonNull(transactionId, "Transaction ID must not be null.");
        
        validatePositiveAmount(amount);

        if (this.balance.getAmountInCents() < amount.getAmountInCents()) {
            return false;
        }
        if (!currencyConversion.isUSD()) {
            long convertedAmount = Math.round(amount.getAmountInCents() * currencyConversion.getConversionRate());
            amount = Money.ofCents(convertedAmount);
        }
        if (requiresPin) {
            System.out.println("PIN verification required for transaction: " + transactionId);
        }
        System.out.println("Transaction authorized at " + merchantInfo.toString());
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

    public boolean validateAccountForLoan(Money loanAmount, CreditHistory creditHistory) {
        Objects.requireNonNull(loanAmount, "Loan amount must not be null.");
        Objects.requireNonNull(creditHistory, "Credit history must not be null.");
        
        Money minimumBalance = Money.ofCents(loanAmount.getAmountInCents() / 10);
        if (this.balance.getAmountInCents() < minimumBalance.getAmountInCents()) {
            return false;
        }
        if (creditHistory.isPoorCredit() && creditHistory.getYearsOfHistory() < 2) {
            return false;
        }
        if (creditHistory.isFairCredit() && creditHistory.getYearsOfHistory() < 1) {
            return false;
        }

        if (loanAmount.getAmountInCents() > 100000000 && creditHistory.getCreditScore() < 750) {
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
