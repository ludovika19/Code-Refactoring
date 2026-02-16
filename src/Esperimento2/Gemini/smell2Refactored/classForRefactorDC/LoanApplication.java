package Esperimento2.Gemini.smell2Refactored.classForRefactorDC;

import utility.Money;
import java.util.Objects;

public class LoanApplication {
    private final Money loanAmount;
    private final int creditScore;
    private final int yearsOfHistory;

    public LoanApplication(Money loanAmount, int creditScore, int yearsOfHistory) {
        this.loanAmount = Objects.requireNonNull(loanAmount);
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