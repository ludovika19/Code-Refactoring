package Esperimento2.Gemini.smell13Refactored.classForRefactorSWST;


public enum CardType {
    PLATINUM("3x points"),
    GOLD("2x points"),
    SILVER("1.5x points"),
    BRONZE("1x points"),
    OTHER("No rewards"); // Represents the default case

    private final String rewardMultiplier;

    CardType(String rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public String getRewardMultiplier() {
        return this.rewardMultiplier;
    }

    public static CardType fromString(String type) {
        try {
            return CardType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return OTHER;
        }
    }
}
