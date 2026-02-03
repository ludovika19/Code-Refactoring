package Esperimento1.Gemini.smell8Refactored.classForRefactorLPL;


import utility.Money;
import java.util.Objects;

// Parameter Object for authorizing a transaction
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
        this.amount = Objects.requireNonNull(amount, "Amount must not be null.");
        this.merchantName = Objects.requireNonNull(merchantName, "Merchant name must not be null.");
        this.merchantCategory = Objects.requireNonNull(merchantCategory, "Merchant category must not be null.");
        this.location = Objects.requireNonNull(location, "Location must not be null.");
        this.currency = Objects.requireNonNull(currency, "Currency must not be null.");
        this.conversionRate = conversionRate;
        this.requiresPin = requiresPin;
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID must not be null.");
    }

    // Getters for all fields
    public Money getAmount() { return amount; }
    public String getMerchantName() { return merchantName; }
    public String getMerchantCategory() { return merchantCategory; }
    public String getLocation() { return location; }
    public String getCurrency() { return currency; }
    public double getConversionRate() { return conversionRate; }
    public boolean isPinRequired() { return requiresPin; }
    public String getTransactionId() { return transactionId; }
}