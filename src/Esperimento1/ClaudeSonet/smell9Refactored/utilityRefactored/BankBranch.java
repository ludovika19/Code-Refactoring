package Esperimento1.ClaudeSonet.smell9Refactored.utilityRefactored;

public class BankBranch {
    private Manager manager;
    private Address address;
    
    // Existing methods...
    
    public Manager getManager() {
        return manager;
    }
    
    public Address getAddress() {
        return address;
    }
    
    // NEW: Hide Delegate methods to eliminate message chains
    public String getManagerName() {
        return this.manager.getPersonalInfo().getName();
    }
    
    public String getCityName() {
        return this.address.getCity().getName();
    }
}
