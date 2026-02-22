package Esperimento2.Claude.smell13Refactored.classForRefactorSWST;


public enum CardType {
    PLATINUM {
        @Override
        public String getRewardMultiplier() {
            return "3x points";
        }
    },
    GOLD {
        @Override
        public String getRewardMultiplier() {
            return "2x points";
        }
    },
    SILVER {
        @Override
        public String getRewardMultiplier() {
            return "1.5x points";
        }
    },
    BRONZE {
        @Override
        public String getRewardMultiplier() {
            return "1x points";
        }
    },
    UNKNOWN {
        @Override
        public String getRewardMultiplier() {
            return "No rewards";
        }
    };

    public abstract String getRewardMultiplier();

    public static CardType fromString(String type) {
        if (type == null) {
            return UNKNOWN;
        }
        try {
            return CardType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
