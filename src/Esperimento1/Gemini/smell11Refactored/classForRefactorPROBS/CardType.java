package Esperimento1.Gemini.smell11Refactored.classForRefactorPROBS;

public enum CardType {
    PLATINUM("3x points"),
    GOLD("2x points"),
    SILVER("1.5x points"),
    BRONZE("1x points"),
    NONE("No rewards");

    private final String rewardMultiplier;

    CardType(String rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public String getRewardMultiplier() {
        return rewardMultiplier;
    }
}