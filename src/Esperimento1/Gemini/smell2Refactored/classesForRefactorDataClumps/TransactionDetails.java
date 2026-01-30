package Esperimento1.Gemini.smell2Refactored.classesForRefactorDataClumps;


import utility.Money;

public class TransactionDetails {
    private final Money amount;
    private final String merchantName;
    private final String merchantCategory;
    private final String location;
    private final String currency;
    private final double conversionRate;
    private final boolean requiresPin;
    private final String transactionId;

    public TransactionDetails(Money amount, String merchantName, String merchantCategory, String location, String currency, double conversionRate, boolean requiresPin, String transactionId) {
        this.amount = amount;
        this.merchantName = merchantName;
        this.merchantCategory = merchantCategory;
        this.location = location;
        this.currency = currency;
        this.conversionRate = conversionRate;
        this.requiresPin = requiresPin;
        this.transactionId = transactionId;
    }

    public Money getAmount() {
        return amount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMerchantCategory() {
        return merchantCategory;
    }
    
    public String getLocation() {
        return location;
    }

    public String getCurrency() {
        return currency;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public boolean isPinRequired() {
        return requiresPin;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
