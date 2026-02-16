package Esperimento2.Gemini.smell9Refactored.utilityRefactored;


public class BankBranch {

    private final BranchID branchId;
    private final Address address;
    private Manager manager;

    public BankBranch(BranchID branchId, Address address) {
        this.branchId = branchId;
        this.address = address;
    }

    // New delegating method to hide the chain: getManager().getPersonalInfo().getName()
    public String getManagerName() {
        if (this.manager != null && this.manager.getPersonalInfo() != null) {
            return this.manager.getPersonalInfo().getName();
        }
        return null; // Or throw an exception, depending on business logic
    }

    // New delegating method to hide the chain: getAddress().getCity().getName()
    public String getCityName() {
        if (this.address != null && this.address.getCity() != null) {
            return this.address.getCity().getName();
        }
        return null; // Or throw an exception
    }
    
    // Existing methods
    public Address getAddress() {
        return address;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public BranchID getBranchId() {
        return branchId;
    }
} .java {
    
}
