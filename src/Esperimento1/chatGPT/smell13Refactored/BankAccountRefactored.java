package Esperimento1.chatGPT.smell13Refactored;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import utility.*;

public class BankAccountRefactored {

    private static final Map<String, Double> TRANSACTION_FEES;
    private static final double DEFAULT_TRANSACTION_FEE = 5.00;

    private static final Map<String, Integer> MAX_DAILY_WITHDRAWALS_BY_TIER;
    private static final int DEFAULT_MAX_DAILY_WITHDRAWALS = 1;

    private static final Map<String, String> REWARD_MULTIPLIERS_BY_CARD_TYPE;
    private static final String DEFAULT_REWARD_MULTIPLIER = "No rewards";

    static {
        // Table-driven configuration for transaction fees
        TRANSACTION_FEES = new HashMap<>();
        TRANSACTION_FEES.put("WIRE", 25.00);
        TRANSACTION_FEES.put("ATM", 2.50);
        TRANSACTION_FEES.put("TRANSFER", 0.00);
        TRANSACTION_FEES.put("CHECK", 1.00);

        // Table-driven configuration for max daily withdrawals
        MAX_DAILY_WITHDRAWALS_BY_TIER = new HashMap<>();
        MAX_DAILY_WITHDRAWALS_BY_TIER.put("BASIC", 3);
        MAX_DAILY_WITHDRAWALS_BY_TIER.put("STANDARD", 5);
        MAX_DAILY_WITHDRAWALS_BY_TIER.put("PREMIUM", 10);
        MAX_DAILY_WITHDRAWALS_BY_TIER.put("VIP", -1);

        // Table-driven configuration for reward multipliers
        REWARD_MULTIPLIERS_BY_CARD_TYPE = new HashMap<>();
        REWARD_MULTIPLIERS_BY_CARD_TYPE.put("PLATINUM", "3x points");
        REWARD_MULTIPLIERS_BY_CARD_TYPE.put("GOLD", "2x points");
        REWARD_MULTIPLIERS_BY_CARD_TYPE.put("SILVER", "1.5x points");
        REWARD_MULTIPLIERS_BY_CARD_TYPE.put("BRONZE", "1x points");
    }

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

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

    /**
     * Refactored from switch(transactionType).
     * Uses a table-driven approach: transaction type → fee.
     */
    public double getTransactionFee(String transactionType) {
        if (transactionType == null) {
            return DEFAULT_TRANSACTION_FEE;
        }
        return TRANSACTION_FEES.getOrDefault(transactionType, DEFAULT_TRANSACTION_FEE);
    }

    /**
     * Refactored from switch(accountTier).
     * Uses a table-driven approach: account tier → max daily withdrawals.
     */
    public int getMaxDailyWithdrawals(String accountTier) {
        if (accountTier == null) {
            return DEFAULT_MAX_DAILY_WITHDRAWALS;
        }
        return MAX_DAILY_WITHDRAWALS_BY_TIER.getOrDefault(accountTier, DEFAULT_MAX_DAILY_WITHDRAWALS);
    }

    /**
     * Refactored from switch(cardType).
     * Uses a table-driven approach: card type → reward multiplier.
     */
    public String getRewardMultiplier(String cardType) {
        if (cardType == null) {
            return DEFAULT_REWARD_MULTIPLIER;
        }
        return REWARD_MULTIPLIERS_BY_CARD_TYPE.getOrDefault(cardType, DEFAULT_REWARD_MULTIPLIER);
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
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

    /**
     * Refactored to improve readability while preserving the logic of the
     * original if-chain (line 92 in the original code).
     *
     * This is a preparatory step towards extracting a separate loan policy
     * (e.g., a Strategy) if needed.
     */
    public boolean validateAccountForLoan(Money loanAmount, int creditScore, int yearsOfHistory) {
        if (!isCreditScoreInRange(creditScore)) {
            return false;
        }
        if (!isYearsOfHistoryNonNegative(yearsOfHistory)) {
            return false;
        }
        if (!hasMinimumBalanceForLoan(loanAmount)) {
            return false;
        }
        if (!meetsCreditHistoryCombination(creditScore, yearsOfHistory)) {
            return false;
        }
        if (!allowsLargeLoan(loanAmount, creditScore)) {
            return false;
        }
        return true;
    }

    private boolean isCreditScoreInRange(int creditScore) {
        return creditScore >= 300 && creditScore <= 850;
    }

    private boolean isYearsOfHistoryNonNegative(int yearsOfHistory) {
        return yearsOfHistory >= 0;
    }

    private boolean hasMinimumBalanceForLoan(Money loanAmount) {
        Money minimumBalance = Money.ofCents(loanAmount.getAmountInCents() / 10);
        return this.balance.getAmountInCents() >= minimumBalance.getAmountInCents();
    }

    private boolean meetsCreditHistoryCombination(int creditScore, int yearsOfHistory) {
        if (creditScore < 600 && yearsOfHistory < 2) {
            return false;
        }
        if (creditScore >= 600 && creditScore < 700 && yearsOfHistory < 1) {
            return false;
        }
        return true;
    }

    private boolean allowsLargeLoan(Money loanAmount, int creditScore) {
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