package Esperimento2.chatGPT.smell13Refactored.classForRefactorSWST;


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

    public static CardType fromString(String value) {
        if (value == null) {
            return OTHER;
        }
        try {
            return CardType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return OTHER;
        }
    }
}
