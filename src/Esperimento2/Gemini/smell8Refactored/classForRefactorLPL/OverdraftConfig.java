package Esperimento2.Gemini.smell8Refactored.classForRefactorLPL;

import utility.Money;
import java.util.Objects;
import Esperimento2.Gemini.smell8Refactored.BankAccount;

public class OverdraftConfig {
    private final Money overdraftLimit;
    private final boolean autoTransferFromSavings;
    private final BankAccount savingsAccount;
    private final boolean notifyOnOverdraft;
    private final String notificationEmail;

    public OverdraftConfig(Money overdraftLimit, boolean autoTransferFromSavings, BankAccount savingsAccount, boolean notifyOnOverdraft, String notificationEmail) {
        this.overdraftLimit = Objects.requireNonNull(overdraftLimit, "Overdraft limit must not be null.");
        this.autoTransferFromSavings = autoTransferFromSavings;
        this.savingsAccount = savingsAccount;
        this.notifyOnOverdraft = notifyOnOverdraft;
        this.notificationEmail = notificationEmail;

        if (autoTransferFromSavings) {
            Objects.requireNonNull(savingsAccount, "Savings account must not be null for auto-transfer.");
        }
        if (notifyOnOverdraft) {
            Objects.requireNonNull(notificationEmail, "Notification email must not be null for overdraft notifications.");
        }
    }

    public Money getOverdraftLimit() {
        return overdraftLimit;
    }

    public boolean isAutoTransferFromSavings() {
        return autoTransferFromSavings;
    }

    public BankAccount getSavingsAccount() {
        return savingsAccount;
    }

    public boolean isNotifyOnOverdraft() {
        return notifyOnOverdraft;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }
}