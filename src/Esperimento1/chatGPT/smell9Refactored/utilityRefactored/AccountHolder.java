
package Esperimento1.chatGPT.smell9Refactored.utilityRefactored;

import java.util.Objects;


public class AccountHolder {

    private ContactInfo contactInfo;

    // existing fields, constructor, getters, etc.

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    // New delegation-hiding method

    public PhoneNumber getPrimaryPhoneNumber() {
        return contactInfo.getPrimaryPhone().getNumber();
    }
}