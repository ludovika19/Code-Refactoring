package Esperimento1.ClaudeSonet.smell11Refactored.classForRefactorPROBS;

public enum AccountTier {
    BASIC(3),
    STANDARD(5),
    PREMIUM(10),
    VIP(-1),
    DEFAULT(1);

    private final int maxDailyWithdrawals;

    AccountTier(int maxDailyWithdrawals) {
        this.maxDailyWithdrawals = maxDailyWithdrawals;
    }

    public int getMaxDailyWithdrawals() {
        return maxDailyWithdrawals;
    }

    public boolean hasUnlimitedWithdrawals() {
        return maxDailyWithdrawals == -1;
    }
}
