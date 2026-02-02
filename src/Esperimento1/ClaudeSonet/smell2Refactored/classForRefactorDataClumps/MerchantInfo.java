package Esperimento1.ClaudeSonet.smell2Refactored.classForRefactorDataClumps;

import java.util.Objects;

public class MerchantInfo {
    private final String merchantName;
    private final String merchantCategory;
    private final String location;

    public MerchantInfo(String merchantName, String merchantCategory, String location) {
        this.merchantName = Objects.requireNonNull(merchantName, "Merchant name must not be null.");
        this.merchantCategory = Objects.requireNonNull(merchantCategory, "Merchant category must not be null.");
        this.location = Objects.requireNonNull(location, "Location must not be null.");
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

    @Override
    public String toString() {
        return merchantName + " (" + merchantCategory + ") in " + location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MerchantInfo that = (MerchantInfo) o;
        return Objects.equals(merchantName, that.merchantName) &&
               Objects.equals(merchantCategory, that.merchantCategory) &&
               Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merchantName, merchantCategory, location);
    }
}
