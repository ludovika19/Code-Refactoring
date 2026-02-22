package Esperimento2.Claude.smell13Refactored.classForRefactorSWST;


public enum AccountTier {
    BASIC {
        @Override
        public int getMaxDailyWithdrawals() {
            return 3;
        }
    },
    STANDARD {
        @Override
        public int getMaxDailyWithdrawals() {
            return 5;
        }
    },
    PREMIUM {
        @Override
        public int getMaxDailyWithdrawals() {
            return 10;
        }
    },
    VIP {
        @Override
        public int getMaxDailyWithdrawals() {
            return -1;
        }
    },
    UNKNOWN {
        @Override
        public int getMaxDailyWithdrawals() {
            return 1;
        }
    };

    public abstract int getMaxDailyWithdrawals();

    public static AccountTier fromString(String tier) {
        if (tier == null) {
            return UNKNOWN;
        }
        try {
            return AccountTier.valueOf(tier.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
