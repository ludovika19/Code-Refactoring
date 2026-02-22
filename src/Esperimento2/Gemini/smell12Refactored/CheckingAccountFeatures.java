package Esperimento2.Gemini.smell12Refactored;

public class CheckingAccountFeatures implements AccountFeatures {
    private boolean isInternational = false;

    @Override
    public boolean supportsChecks() {
        return true;
    }

    @Override
    public void issueCheckbook(int numberOfChecks) {
        System.out.println("Issuing " + numberOfChecks + " checks");
    }

    @Override
    public void enableInternationalTransactions() {
        this.isInternational = true;
        System.out.println("International transactions have been enabled.");
    }
}
