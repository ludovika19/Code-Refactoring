package Esperimento2.chatGPT.smell11Refactored.classForRefactorPROBS;


public enum CardType {
    PLATINUM,
    GOLD,
    SILVER,
    BRONZE,
    OTHER;

    public static CardType fromString(String value) {
        if (value == null) {
            return OTHER;
        }
        switch (value) {
            case "PLATINUM":
                return PLATINUM;
            case "GOLD":
                return GOLD;
            case "SILVER":
                return SILVER;
            case "BRONZE":
                return BRONZE;
            default:
                return OTHER;
        }
    }

    public String rewardMultiplier() {
        switch (this) {
            case PLATINUM:
                return "3x points";
            case GOLD:
                return "2x points";
            case SILVER:
                return "1.5x points";
            case BRONZE:
                return "1x points";
            default:
                return "No rewards";
        }
    }
}
