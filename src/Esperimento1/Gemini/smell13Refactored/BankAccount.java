package Esperimento1.Gemini.smell13Refactored;


import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import utility.AccountHolder;
import utility.AccountID;
import utility.Money;

// --- Refactoring for getTransactionFee ---
// Using Replace Conditional with Polymorphism
abstract class TransactionType {
    abstract double getFee();
}

class WireTransaction extends TransactionType {
    @Override
    double getFee() { return 25.00; }
}

class AtmTransaction extends TransactionType {
    @Override
    double getFee() { return 2.50; }
}

class TransferTransaction extends TransactionType {
    @Override
    double getFee() { return 0.00; }
}

class CheckTransaction extends TransactionType {
    @Override
    double getFee() { return 1.00; }
}

class DefaultTransaction extends TransactionType {
    @Override
    double getFee() { return 5.00; }
}


public class BankAccount {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

    // --- Refactoring for getMaxDailyWithdrawals and getRewardMultiplier ---
    // Using a Map to replace switch statements
    private static final Map<String, Integer> MAX_DAILY_WITHDRAWALS_BY_TIER = Map.of(
        "BASIC", 3,
        "STANDARD", 5,
        "PREMIUM", 10,
        "VIP", -1 // Represents unlimited
    );

    private static final Map<String, String> REWARD_MULTIPLIER_BY_CARD_TYPE = Map.of(
        "PLATINUM", "3x points",
        "GOLD", "2x points",
        "SILVER", "1.5x points",
        "BRONZE", "1x points"
    );

    private static final Map<String, Supplier<TransactionType>> TRANSACTION_TYPE_FACTORY = Map.of(
        "WIRE", WireTransaction::new,
        "ATM", AtmTransaction::new,
        "TRANSFER", TransferTransaction::new,
        "CHECK", CheckTransaction::new
    );

    public BankAccount(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    public double getTransactionFee(String transactionType) {
        TransactionType transaction = TRANSACTION_TYPE_FACTORY
            .getOrDefault(transactionType, DefaultTransaction::new)
            .get();
        return transaction.getFee();
    }

    public int getMaxDailyWithdrawals(String accountTier) {
        return MAX_DAILY_WITHDRAWALS_BY_TIER.getOrDefault(accountTier, 1);
    }

    public String getRewardMultiplier(String cardType) {
        return REWARD_MULTIPLIER_BY_CARD_TYPE.getOrDefault(cardType, "No rewards");
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
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
    
    // --- Refactoring for validateAccountForLoan ---
    // Using Decompose Conditional
    private boolean isCreditScoreValid(int creditScore) {
        return creditScore >= 300 && creditScore <= 850;
    }

    private boolean hasSufficientBalanceForLoan(Money loanAmount) {
        Money minimumBalance = Money.ofCents(loanAmount.getAmountInCents() / 10);
        return this.balance.getAmountInCents() >= minimumBalance.getAmountInCents();
    }
    
    private boolean meetsCreditHistoryRequirements(int creditScore, int yearsOfHistory) {
        if (creditScore < 600 && yearsOfHistory < 2) {
            return false;
        }
        if (creditScore >= 600 && creditScore < 700 && yearsOfHistory < 1) {
            return false;
        }
        return true;
    }
    
    private boolean isEligibleForLargeLoan(Money loanAmount, int creditScore) {
        if (loanAmount.getAmountInCents() > 100000000 && creditScore < 750) {
            return false;
        }
        return true;
    }

    public boolean validateAccountForLoan(Money loanAmount, int creditScore, int yearsOfHistory) {
        if (!isCreditScoreValid(creditScore) || yearsOfHistory < 0) {
            return false;
        }
        if (!hasSufficientBalanceForLoan(loanAmount)) {
            return false;
        }
        if (!meetsCreditHistoryRequirements(creditScore, yearsOfHistory)) {
            return false;
        }
        if (!isEligibleForLargeLoan(loanAmount, creditScore)) {
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