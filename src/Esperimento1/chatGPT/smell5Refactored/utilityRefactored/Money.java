package Esperimento1.chatGPT.smell5Refactored.utilityRefactored;


public class Money {

    private final long amountInCents;

    private Money(long amountInCents) {
        this.amountInCents = amountInCents;
    }

    public static Money ofCents(long cents) {
        return new Money(cents);
    }

    public boolean isPositive() {
        return amountInCents > 0;
    }

    public long getAmountInCents() {
        return amountInCents;
    }

    public Money add(Money other) {
        return new Money(this.amountInCents + other.amountInCents);
    }

    public Money subtract(Money other) {
        return new Money(this.amountInCents - other.amountInCents);
    }

    // Extracted + moved behavior to avoid Feature Envy in BankAccountSmelly
    public boolean isGreaterThanOrEqual(Money other) {
        return this.amountInCents >= other.amountInCents;
    }

    public boolean isGreaterThan(Money other) {
        return this.amountInCents > other.amountInCents;
    }

    @Override
    public String toString() {
        return amountInCents + " cents";
    }
}
