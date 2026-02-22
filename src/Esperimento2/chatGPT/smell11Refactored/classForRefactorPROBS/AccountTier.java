package Esperimento2.chatGPT.smell11Refactored.classForRefactorPROBS;

public enum AccountTier {
    BASIC,
    STANDARD,
    PREMIUM,
    VIP,
    OTHER;

    public static AccountTier fromString(String value) {
        if (value == null) {
            return OTHER;
        }
        switch (value) {
            case "BASIC":
                return BASIC;
            case "STANDARD":
                return STANDARD;
            case "PREMIUM":
                return PREMIUM;
            case "VIP":
                return VIP;
            default:
                return OTHER;
        }
    }
}
