package Esperimento1.ClaudeSonet.smell8Refactored.classForRefactorLPL;

import Esperimento1.ClaudeSonet.smell8Refactored.BankAccountSmelly;

public class OverdraftProtectionConfig {
    public final boolean autoTransferFromSavings;
    public final BankAccountSmelly savingsAccount;
    public final boolean notifyOnOverdraft;
    public final String notificationEmail;

    public OverdraftProtectionConfig(boolean autoTransferFromSavings, BankAccountSmelly savingsAccount, boolean notifyOnOverdraft, String notificationEmail) {
        this.autoTransferFromSavings = autoTransferFromSavings;
        this.savingsAccount = savingsAccount;
        this.notifyOnOverdraft = notifyOnOverdraft;
        this.notificationEmail = notificationEmail;
    }
}