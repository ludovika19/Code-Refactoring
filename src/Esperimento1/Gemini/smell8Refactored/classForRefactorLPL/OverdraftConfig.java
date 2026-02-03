package Esperimento1.Gemini.smell8Refactored.classForRefactorLPL;


import Esperimento1.Gemini.smell8Refactored.BankAccount;
import utility.Money;

// Parameter Object for configuring overdraft protection
public class OverdraftConfig {
    private final Money overdraftLimit;
    private final boolean autoTransferFromSavings;
    private final BankAccount savingsAccount;
    private final boolean notifyOnOverdraft;
    private final String notificationEmail;

    public OverdraftConfig(Money overdraftLimit, boolean autoTransferFromSavings, BankAccount savingsAccount, boolean notifyOnOverdraft, String notificationEmail) {
        this.overdraftLimit = overdraftLimit;
        this.autoTransferFromSavings = autoTransferFromSavings;
        this.savingsAccount = savingsAccount;
        this.notifyOnOverdraft = notifyOnOverdraft;
        this.notificationEmail = notificationEmail;
    }

    // Getters for all fields
    public Money getOverdraftLimit() { return overdraftLimit; }
    public boolean isAutoTransferFromSavings() { return autoTransferFromSavings; }
    public BankAccount getSavingsAccount() { return savingsAccount; }
    public boolean shouldNotifyOnOverdraft() { return notifyOnOverdraft; }
    public String getNotificationEmail() { return notificationEmail; }
}