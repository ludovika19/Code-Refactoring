package Esperimento1.Gemini.smell2Refactored.classesForRefactorDataClumps;

import utility.Money;

public class LoanDetails {
    private final Money loanAmount;
    private final int creditScore;
    private final int yearsOfHistory;

    public LoanDetails(Money loanAmount, int creditScore, int yearsOfHistory) {
        this.loanAmount = loanAmount;
        this.creditScore = creditScore;
        this.yearsOfHistory = yearsOfHistory;
    }

    public Money getLoanAmount() {
        return loanAmount;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public int getYearsOfHistory() {
        return yearsOfHistory;
    }
}
