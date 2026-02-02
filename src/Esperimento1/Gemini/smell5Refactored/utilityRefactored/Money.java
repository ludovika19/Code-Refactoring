package Esperimento1.Gemini.smell5Refactored.utilityRefactored;

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
     * New method moved from BankAccountSmelly.
     * Compares if this Money amount is greater than another.
     */
    public boolean isGreaterThan(Money other) {
        return this.amountInCents > other.getAmountInCents();
    }

    /**
     * New method moved from BankAccountSmelly.
     * Compares if this Money amount is greater than or equal to another.
     */
    public boolean isGreaterThanOrEqualTo(Money other) {
        return this.amountInCents >= other.getAmountInCents();
    }

    @Override
    public String toString() {
        return String.valueOf(amountInCents);
    }
}