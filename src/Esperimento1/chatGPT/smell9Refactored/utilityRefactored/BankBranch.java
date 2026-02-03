package Esperimento1.chatGPT.smell9Refactored.utilityRefactored;

public class BankBranch {

    private Manager manager;
    private Address address;

    // existing fields, constructor, getters, etc.

    public Manager getManager() {
        return manager;
    }

    public Address getAddress() {
        return address;
    }

    // New delegation-hiding methods

    public String getManagerName() {
        return manager.getPersonalInfo().getName();
    }

    public String getCityName() {
        return address.getCity().getName();
    }
}