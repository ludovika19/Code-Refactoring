package Esperimento2.Claude.smell11Refactored.classForRefactorPROBS;

public enum CardType {
    PLATINUM("3x points"),
    GOLD("2x points"),
    SILVER("1.5x points"),
    BRONZE("1x points"),
    OTHER("No rewards");

    private final String rewardMultiplier;

    CardType(String rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public String getRewardMultiplier() {
        return rewardMultiplier;
    }

    public static CardType fromString(String type) {
        try {
            return CardType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return OTHER;
        }
    }
}
