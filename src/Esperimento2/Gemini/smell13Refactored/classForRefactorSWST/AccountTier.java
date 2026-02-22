package Esperimento2.Gemini.smell13Refactored.classForRefactorSWST;


public enum AccountTier {
    BASIC(3),
    STANDARD(5),
    PREMIUM(10),
    VIP(-1), // Represents unlimited
    OTHER(1); // Represents the default case

    private final int maxDailyWithdrawals;

    AccountTier(int maxDailyWithdrawals) {
        this.maxDailyWithdrawals = maxDailyWithdrawals;
    }

    public int getMaxDailyWithdrawals() {
        return this.maxDailyWithdrawals;
    }

    public static AccountTier fromString(String tier) {
        try {
            return AccountTier.valueOf(tier.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return OTHER;
        }
    }
}
