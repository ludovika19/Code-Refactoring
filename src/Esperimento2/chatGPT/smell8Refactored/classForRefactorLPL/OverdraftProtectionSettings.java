package Esperimento2.chatGPT.smell8Refactored.classForRefactorLPL;

import java.util.Objects;
import utility.Money;

public class OverdraftProtectionSettings {
    private final Money overdraftLimit;
    private final boolean autoTransferFromSavings;
    private final BankAccountSmelly savingsAccount;
    private final boolean notifyOnOverdraft;
    private final String notificationEmail;

    public OverdraftProtectionSettings(Money overdraftLimit,
                                       boolean autoTransferFromSavings,
                                       BankAccountSmelly savingsAccount,
                                       boolean notifyOnOverdraft,
                                       String notificationEmail) {
        this.overdraftLimit = Objects.requireNonNull(overdraftLimit, "Overdraft limit must not be null.");
        this.autoTransferFromSavings = autoTransferFromSavings;
        this.savingsAccount = savingsAccount;
        this.notifyOnOverdraft = notifyOnOverdraft;
        this.notificationEmail = notificationEmail;
    }

    public Money getOverdraftLimit() {
        return overdraftLimit;
    }

    public boolean isAutoTransferFromSavings() {
        return autoTransferFromSavings;
    }

    public BankAccountSmelly getSavingsAccount() {
        return savingsAccount;
    }

    public boolean isNotifyOnOverdraft() {
        return notifyOnOverdraft;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }
}