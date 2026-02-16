package Esperimento2.chatGPT.smell9Refactored.utilityRefactored;

public class AccountHolder {

    private ContactInfo contactInfo;

    // Existing getter (implied by original chain)
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    // --- New delegate method to hide message chain ---

    /**
     * Hide Delegate for: getContactInfo().getPrimaryPhone().getNumber()
     */
    public PhoneNumber getPrimaryPhoneNumber() {
        return contactInfo.getPrimaryPhone().getNumber();
    }
}