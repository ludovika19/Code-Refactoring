package Esperimento2.Gemini.smell2Refactored.classForRefactorDC;

import utility.Money;
import java.util.Objects;

public class TransactionDetails {
    private Money amount;
    private final String merchantName;
    private final String merchantCategory;
    private final String location;
    private final String currency;
    private final double conversionRate;
    private final boolean requiresPin;
    private final String transactionId;

    public TransactionDetails(Money amount, String merchantName, String merchantCategory, String location, String currency, double conversionRate, boolean requiresPin, String transactionId) {
        this.amount = Objects.requireNonNull(amount);
        this.merchantName = Objects.requireNonNull(merchantName);
        this.merchantCategory = Objects.requireNonNull(merchantCategory);
        this.location = Objects.requireNonNull(location);
        this.currency = Objects.requireNonNull(currency);
        this.conversionRate = conversionRate;
        this.requiresPin = requiresPin;
        this.transactionId = Objects.requireNonNull(transactionId);
    }
    
    public void setAmount(Money amount) {
        this.amount = amount;
    }

    // Getters for all fields
    public Money getAmount() { return amount; }
    public String getMerchantName() { return merchantName; }
    public String getMerchantCategory() { return merchantCategory; }
    public String getLocation() { return location; }
    public String getCurrency() { return currency; }
    public double getConversionRate() { return conversionRate; }
    public boolean isRequiresPin() { return requiresPin; }
    public String getTransactionId() { return transactionId; }
}