package Esperimento1.chatGPT.smell11Refactored.classForRefactorPROBS;


public enum CardType {
    PLATINUM("3x points"),
    GOLD("2x points"),
    SILVER("1.5x points"),
    BRONZE("1x points"),
    NONE("No rewards");

    private final String rewardMultiplierDescription;

    CardType(String rewardMultiplierDescription) {
        this.rewardMultiplierDescription = rewardMultiplierDescription;
    }

    public String getRewardMultiplierDescription() {
        return rewardMultiplierDescription;
    }
}
