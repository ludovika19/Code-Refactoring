package Esperimento1.ClaudeSonet.smell2Refactored.classForRefactorDataClumps;

import java.util.Objects;

public class CurrencyConversion {
    private final String currency;
    private final double conversionRate;

    public CurrencyConversion(String currency, double conversionRate) {
        this.currency = Objects.requireNonNull(currency, "Currency must not be null.");
        if (conversionRate <= 0) {
            throw new IllegalArgumentException("Conversion rate must be positive.");
        }
        this.conversionRate = conversionRate;
    }

    public String getCurrency() {
        return currency;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public boolean isUSD() {
        return "USD".equals(currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyConversion that = (CurrencyConversion) o;
        return Double.compare(that.conversionRate, conversionRate) == 0 &&
               Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, conversionRate);
    }
}