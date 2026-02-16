package Esperimento2.Gemini.smell5Refactored.utilityRefactored;

public class Money {

    private final long amountInCents;

    private Money(long amountInCents) {
        this.amountInCents = amountInCents;
    }

    public static Money ofCents(long amountInCents) {
        return new Money(amountInCents);
    }

    public long getAmountInCents() {
        return amountInCents;
    }

    public boolean isPositive() {
        return this.amountInCents > 0;
    }

    public Money add(Money other) {
        return new Money(this.amountInCents + other.amountInCents);
    }

    public Money subtract(Money other) {
        return new Money(this.amountInCents - other.amountInCents);
    }

    /**
     * New method to encapsulate comparison logic.
     * Used by BankAccount.canAffordPurchase.
     */
    public boolean isGreaterThanOrEqual(Money other) {
        return this.amountInCents >= other.amountInCents;
    }

    /**
     * New method to encapsulate comparison logic.
     * Used by BankAccount.isBalanceGreaterThan.
     */
    public boolean isGreaterThan(Money other) {
        return this.amountInCents > other.amountInCents;
    }

    @Override
    public String toString() {
        return String.valueOf(amountInCents);
    }
}