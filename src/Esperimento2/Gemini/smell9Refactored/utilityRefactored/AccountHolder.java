package Esperimento2.Gemini.smell9Refactored.utilityRefactored;


public class AccountHolder {

    private final PersonInfo personalInfo;
    private final ContactInfo contactInfo;

    public AccountHolder(PersonInfo personalInfo, ContactInfo contactInfo) {
        this.personalInfo = personalInfo;
        this.contactInfo = contactInfo;
    }

    // New delegating method to hide the chain: getContactInfo().getPrimaryPhone().getNumber()
    public PhoneNumber getPrimaryPhoneNumber() {
        if (this.contactInfo != null && this.contactInfo.getPrimaryPhone() != null) {
            return this.contactInfo.getPrimaryPhone().getNumber();
        }
        return null; // Or throw an exception
    }

    // Existing methods
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public PersonInfo getPersonalInfo() {
        return personalInfo;
    }
}