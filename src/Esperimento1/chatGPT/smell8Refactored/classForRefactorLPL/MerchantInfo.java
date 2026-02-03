package Esperimento1.chatGPT.smell8Refactored.classForRefactorLPL;


/**
 * Merchant-related information extracted from the original parameter list.
 */
public class MerchantInfo {

    private final String merchantName;
    private final String merchantCategory;
    private final String location;

    public MerchantInfo(String merchantName, String merchantCategory, String location) {
        this.merchantName = merchantName;
        this.merchantCategory = merchantCategory;
        this.location = location;
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
}
