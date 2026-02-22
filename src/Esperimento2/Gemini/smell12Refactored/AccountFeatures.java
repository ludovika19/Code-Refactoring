package Esperimento2.Gemini.smell12Refactored;

public interface AccountFeatures {
    boolean supportsChecks();
    void issueCheckbook(int numberOfChecks);
    void enableInternationalTransactions();
}