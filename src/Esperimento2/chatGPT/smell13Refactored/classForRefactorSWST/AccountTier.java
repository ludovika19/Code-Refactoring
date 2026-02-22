package Esperimento2.chatGPT.smell13Refactored.classForRefactorSWST;

public enum AccountTier {
    BASIC(3),
    STANDARD(5),
    PREMIUM(10),
    VIP(-1),   // -1 means "unlimited" as in original code
    OTHER(1);

    private final int maxDailyWithdrawals;

    AccountTier(int maxDailyWithdrawals) {
        this.maxDailyWithdrawals = maxDailyWithdrawals;
    }

    public int getMaxDailyWithdrawals() {
        return maxDailyWithdrawals;
    }

    public static AccountTier fromString(String value) {
        if (value == null) {
            return OTHER;
        }
        try {
            return AccountTier.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return OTHER;
        }
    }
}
