package Esperimento1.Gemini.smell11Refactored.classForRefactorPROBS;


public enum AccountTier {
    BASIC(3),
    STANDARD(5),
    PREMIUM(10),
    VIP(-1), // Unlimited
    DEFAULT(1);

    private final int maxDailyWithdrawals;

    AccountTier(int maxDailyWithdrawals) {
        this.maxDailyWithdrawals = maxDailyWithdrawals;
    }

    public int getMaxDailyWithdrawals() {
        return maxDailyWithdrawals;
    }
}
