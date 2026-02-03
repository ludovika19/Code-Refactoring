package Esperimento1.ClaudeSonet.smell8Refactored.classForRefactorLPL;


public class TransactionDetails {
    public final String merchantName;
    public final String merchantCategory;
    public final String location;
    public final String currency;
    public final double conversionRate;
    public final boolean requiresPin;
    public final String transactionId;

    public TransactionDetails(String merchantName, String merchantCategory, String location, String currency, double conversionRate, boolean requiresPin, String transactionId) {
        this.merchantName = merchantName;
        this.merchantCategory = merchantCategory;
        this.location = location;
        this.currency = currency;
        this.conversionRate = conversionRate;
        this.requiresPin = requiresPin;
        this.transactionId = transactionId;
    }
}