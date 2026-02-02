package Esperimento1.ClaudeSonet.smell2Refactored.classForRefactorDataClumps;


public class CreditHistory {
    private final int creditScore;
    private final int yearsOfHistory;

    public CreditHistory(int creditScore, int yearsOfHistory) {
        if (creditScore < 300 || creditScore > 850) {
            throw new IllegalArgumentException("Credit score must be between 300 and 850.");
        }
        if (yearsOfHistory < 0) {
            throw new IllegalArgumentException("Years of history must be non-negative.");
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

    public boolean isGoodCredit() {
        return creditScore >= 700;
    }

    public boolean isFairCredit() {
        return creditScore >= 600 && creditScore < 700;
    }

    public boolean isPoorCredit() {
        return creditScore < 600;
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
        return 31 * creditScore + yearsOfHistory;
    }
}