package Esperimento2.Claude.smell9Refactored.classForRefactorMC;

public class AccountHolder {
    // existing fields and methods...
    
    /**
     * Hide Delegate: Encapsulates access to primary phone number
     */
    public PhoneNumber getPrimaryPhoneNumber() {
        return this.getContactInfo().getPrimaryPhone().getNumber();
    }
    
    // existing methods like getContactInfo(), etc.
}