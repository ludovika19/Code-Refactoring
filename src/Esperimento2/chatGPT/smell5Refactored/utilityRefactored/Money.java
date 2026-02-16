package Esperimento2.chatGPT.smell5Refactored.utilityRefactored;

import java.util.Objects;

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
        return amountInCents > 0;
    }

    public Money add(Money other) {
        Objects.requireNonNull(other, "Other money must not be null.");
        return new Money(this.amountInCents + other.amountInCents);
    }

    public Money subtract(Money other) {
        Objects.requireNonNull(other, "Other money must not be null.");
        return new Money(this.amountInCents - other.amountInCents);
    }

    // === Methods moved from BankAccountSmelly (Feature Envy fix) ===

    /**
     * Returns true if this amount is greater than or equal to the given amount.
     */
    public boolean isGreaterOrEqualThan(Money other) {
        Objects.requireNonNull(other, "Other money must not be null.");
        return this.amountInCents >= other.amountInCents;
    }

    /**
     * Returns true if this amount is strictly greater than the given amount.
     */
    public boolean isGreaterThan(Money other) {
        Objects.requireNonNull(other, "Other money must not be null.");
        return this.amountInCents > other.amountInCents;
    }

    @Override
    public String toString() {
        return amountInCents + " cents";
    }
}