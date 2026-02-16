package Esperimento2.Claude.smell5Refactored.utilityRefactored;

public class Money {
    
    private final long amountInCents;
    
    // ... existing methods ...
    
    public long getAmountInCents() {
        return amountInCents;
    }
    
    public boolean isPositive() {
        return amountInCents > 0;
    }
    
    public Money add(Money other) {
        // existing implementation
    }
    
    public Money subtract(Money other) {
        // existing implementation
    }
    
    public static Money ofCents(long cents) {
        // existing implementation
    }
    
    // NEW METHODS - Move the comparison logic here
    public boolean isGreaterThanOrEqualTo(Money other) {
        return this.amountInCents >= other.amountInCents;
    }
    
    public boolean isGreaterThan(Money other) {
        return this.amountInCents > other.amountInCents;
    }
}