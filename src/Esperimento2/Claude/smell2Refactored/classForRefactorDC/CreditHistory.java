package Esperimento2.Claude.smell2Refactored.classForRefactorDC;
import utility.*;

public class CreditHistory {
    private final int creditScore;
    private final int yearsOfHistory;

    public CreditHistory(int creditScore, int yearsOfHistory) {
        if (creditScore < 300 || creditScore > 850) {
            throw new IllegalArgumentException("Credit score must be between 300 and 850.");
        }
        if (yearsOfHistory < 0) {
            throw new IllegalArgumentException("Years of history must not be negative.");
        }
        this.creditScore = creditScore;
        this.yearsOfHistory = yearsOfHistory;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public int getYearsOfHistory() {
        return yearsOfHistory;
    }

    public boolean isValidForLoan(Money loanAmount, Money currentBalance) {
        Money minimumBalance = Money.ofCents(loanAmount.getAmountInCents() / 10);
        if (currentBalance.getAmountInCents() < minimumBalance.getAmountInCents()) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditHistory that = (CreditHistory) o;
        return creditScore == that.creditScore &&
               yearsOfHistory == that.yearsOfHistory;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(creditScore, yearsOfHistory);
    }

    @Override
    public String toString() {
        return "CreditHistory[score=" + creditScore + ", years=" + yearsOfHistory + "]";
    }
}