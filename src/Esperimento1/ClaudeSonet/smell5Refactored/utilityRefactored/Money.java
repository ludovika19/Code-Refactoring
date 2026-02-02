package Esperimento1.ClaudeSonet.smell5Refactored.utilityRefactored;

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

// Add these methods to the Money class to eliminate Feature Envy

public boolean isGreaterThanOrEqualTo(Money other) {
    return this.getAmountInCents() >= other.getAmountInCents();
}

public boolean isGreaterThan(Money other) {
    return this.getAmountInCents() > other.getAmountInCents();
}

    @Override
    public String toString() {
        return String.valueOf(amountInCents);
    }
}