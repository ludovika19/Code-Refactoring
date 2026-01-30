package Esperimento1.chatGPT.smell2Refactored.classesForRefactorDataClumps;

import java.util.Objects;

public class TransactionContext {

    private final String merchantName;
    private final String merchantCategory;
    private final String location;
    private final String currency;
    private final double conversionRate;
    private final boolean requiresPin;
    private final String transactionId;

    public TransactionContext(String merchantName,
                              String merchantCategory,
                              String location,
                              String currency,
                              double conversionRate,
                              boolean requiresPin,
                              String transactionId) {
        this.merchantName = Objects.requireNonNull(merchantName, "Merchant name must not be null.");
        this.merchantCategory = Objects.requireNonNull(merchantCategory, "Merchant category must not be null.");
        this.location = Objects.requireNonNull(location, "Location must not be null.");
        this.currency = Objects.requireNonNull(currency, "Currency must not be null.");
        this.conversionRate = conversionRate;
        this.requiresPin = requiresPin;
        this.transactionId = Objects.requireNonNull(transactionId, "Transaction ID must not be null.");
    }

    public String merchantName() {
        return merchantName;
    }

    public String merchantCategory() {
        return merchantCategory;
    }

    public String location() {
        return location;
    }

    public String currency() {
        return currency;
    }

    public double conversionRate() {
        return conversionRate;
    }

    public boolean requiresPin() {
        return requiresPin;
    }

    public String transactionId() {
        return transactionId;
    }
}