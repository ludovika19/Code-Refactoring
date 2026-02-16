package Esperimento2.Claude.smell9Refactored.classForRefactorMC;

public class BankBranch {
    // existing fields and methods...
    
    /**
     * Hide Delegate: Encapsulates access to manager's name
     */
    public String getManagerName() {
        return this.getManager().getPersonalInfo().getName();
    }
    
    /**
     * Hide Delegate: Encapsulates access to branch city name
     */
    public String getCityName() {
        return this.getAddress().getCity().getName();
    }
    
    // existing methods like getManager(), getAddress(), etc.
}