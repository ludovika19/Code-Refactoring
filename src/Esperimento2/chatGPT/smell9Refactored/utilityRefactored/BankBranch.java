package Esperimento2.chatGPT.smell9Refactored.utilityRefactored;

public class BankBranch {

    private Manager manager;
    private Address address;

    // Existing getters (implied by original chains)
    public Manager getManager() {
        return manager;
    }

    public Address getAddress() {
        return address;
    }

    // --- New delegate methods to hide message chains ---

    /**
     * Hide Delegate for: getManager().getPersonalInfo().getName()
     */
    public String getManagerName() {
        return manager.getPersonalInfo().getName();
    }

    /**
     * Hide Delegate for: getAddress().getCity().getName()
     */
    public String getCityName() {
        return address.getCity().getName();
    }
}