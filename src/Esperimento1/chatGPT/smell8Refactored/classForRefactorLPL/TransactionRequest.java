package Esperimento1.chatGPT.smell8Refactored.classForRefactorLPL;


import utility.Money;

/**
 * Parameters for authorizing a transaction.
 */
public class TransactionRequest {

    private final Money amount;
    private final MerchantInfo merchantInfo;
    private final String currency;
    private final double conversionRate;
    private final boolean requiresPin;
    private final String transactionId;

    public TransactionRequest(
            Money amount,
            MerchantInfo merchantInfo,
            String currency,
            double conversionRate,
            boolean requiresPin,
            String transactionId) {

        this.amount = amount;
        this.merchantInfo = merchantInfo;
        this.currency = currency;
        this.conversionRate = conversionRate;
        this.requiresPin = requiresPin;
        this.transactionId = transactionId;
    }

    public Money getAmount() {
        return amount;
    }

    public MerchantInfo getMerchantInfo() {
        return merchantInfo;
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
