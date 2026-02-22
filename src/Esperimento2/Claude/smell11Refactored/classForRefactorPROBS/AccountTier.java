package Esperimento2.Claude.smell11Refactored.classForRefactorPROBS;

public enum AccountTier {
    BASIC(3),
    STANDARD(5),
    PREMIUM(10),
    VIP(-1), // -1 represents unlimited
    OTHER(1);

    private final int maxDailyWithdrawals;

    AccountTier(int maxDailyWithdrawals) {
        this.maxDailyWithdrawals = maxDailyWithdrawals;
    }

    public int getMaxDailyWithdrawals() {
        return maxDailyWithdrawals;
    }

    public static AccountTier fromString(String tier) {
        try {
            return AccountTier.valueOf(tier.toUpperCase());
        } catch (IllegalArgumentException e) {
            return OTHER;
        }
    }
}