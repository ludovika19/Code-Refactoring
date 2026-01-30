package Esperimento1.chatGPT.smell2Refactored.classesForRefactorDataClumps;

public class CreditProfile {

    private final int creditScore;
    private final int yearsOfHistory;

    public CreditProfile(int creditScore, int yearsOfHistory) {
        this.creditScore = creditScore;
        this.yearsOfHistory = yearsOfHistory;
    }

    public int creditScore() {
        return creditScore;
    }

    public int yearsOfHistory() {
        return yearsOfHistory;
    }
}
