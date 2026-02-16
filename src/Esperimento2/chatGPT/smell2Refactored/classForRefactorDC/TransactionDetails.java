package Esperimento2.chatGPT.smell2Refactored.classForRefactorDC;

import java.util.Objects;

public final class TransactionDetails {

    private final String merchantName;
    private final String merchantCategory;
    private final String location;
    private final String currency;
    private final double conversionRate;
    private final boolean requiresPin;
    private final String transactionId;

    public TransactionDetails(String merchantName,
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

    public boolean isRequiresPin() {
        return requiresPin;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
