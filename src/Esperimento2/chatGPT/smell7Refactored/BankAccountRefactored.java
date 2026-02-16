package Esperimento2.chatGPT.smell7Refactored;

import java.util.Objects;
import utility.*;

public class BankAccountRefactored {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

    public BankAccountRefactored(AccountHolder accountHolder, AccountID accountId) {
        this.accountHolder = Objects.requireNonNull(accountHolder, "Account holder must not be null.");
        this.accountId = Objects.requireNonNull(accountId, "Account ID must not be null.");
        this.balance = Money.ofCents(0);
    }

    private void validatePositiveAmount(Money amount) {
        if (!amount.isPositive()) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }

    public void deposit(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.add(amount);
    }

    // ==============================
    // Account Statement (Long Method → Extract Method)
    // ==============================

    public String generateAccountStatement(String startDate, String endDate) {
        StringBuilder statement = new StringBuilder();
        appendAccountStatementHeader(statement);
        appendAccountStatementAccountInfo(statement, startDate, endDate);
        appendAccountStatementAccountSettings(statement);
        return statement.toString();
    }

    private void appendAccountStatementHeader(StringBuilder statement) {
        statement.append("=== ACCOUNT STATEMENT ===\n");
    }

    private void appendAccountStatementAccountInfo(StringBuilder statement, String startDate, String endDate) {
        statement.append("Account ID: ").append(this.accountId).append("\n");
        statement.append("Account Holder: ").append(this.accountHolder).append("\n");
        statement.append("Statement Period: ").append(startDate).append(" to ").append(endDate).append("\n");
        statement.append("Current Balance: ").append(this.balance).append("\n");
        statement.append("------------------------\n");
    }

    private void appendAccountStatementAccountSettings(StringBuilder statement) {
        statement.append("Account Type: Standard\n");
        statement.append("Interest Rate: 0.00%\n");
        statement.append("Monthly Fee: $0.00\n");
        statement.append("Overdraft Protection: No\n");
        statement.append("========================\n");
    }

    // =================================
    // Loan Validation (Long Method → Decompose Conditional + Extract Method)
    // =================================

    public boolean validateAccountForLoan(Money loanAmount, int creditScore, int yearsOfHistory) {
        if (isCreditScoreOutOfRange(creditScore)) {
            return false;
        }
        if (hasInvalidHistoryYears(yearsOfHistory)) {
            return false;
        }
        if (hasInsufficientBalanceForLoan(loanAmount)) {
            return false;
        }
        if (isHighRiskNewCustomer(creditScore, yearsOfHistory)) {
            return false;
        }
        if (isBorderlineCustomerWithTooLittleHistory(creditScore, yearsOfHistory)) {
            return false;
        }
        if (isLargeLoanWithoutHighEnoughScore(loanAmount, creditScore)) {
            return false;
        }
        return true;
    }

    private boolean isCreditScoreOutOfRange(int creditScore) {
        return creditScore < 300 || creditScore > 850;
    }

    private boolean hasInvalidHistoryYears(int yearsOfHistory) {
        return yearsOfHistory < 0;
    }

    private boolean hasInsufficientBalanceForLoan(Money loanAmount) {
        Money minimumBalance = Money.ofCents(loanAmount.getAmountInCents() / 10);
        return this.balance.getAmountInCents() < minimumBalance.getAmountInCents();
    }

    private boolean isHighRiskNewCustomer(int creditScore, int yearsOfHistory) {
        return creditScore < 600 && yearsOfHistory < 2;
    }

    private boolean isBorderlineCustomerWithTooLittleHistory(int creditScore, int yearsOfHistory) {
        return creditScore >= 600 && creditScore < 700 && yearsOfHistory < 1;
    }

    private boolean isLargeLoanWithoutHighEnoughScore(Money loanAmount, int creditScore) {
        return loanAmount.getAmountInCents() > 100000000 && creditScore < 750;
    }

    // ==================================
    // End-of-Year Processing (Long Method → Extract Method)
    // ==================================

    public void performEndOfYearProcessing(int year, double interestRate, double maintenanceFee, boolean sendStatement) {
        applyYearlyInterest(interestRate);
        applyMaintenanceFee(maintenanceFee);
        if (sendStatement) {
            printYearEndStatement(year, interestRate, maintenanceFee);
        }
    }

    private void applyYearlyInterest(double interestRate) {
        long currentCents = this.balance.getAmountInCents();
        double yearlyInterest = currentCents * interestRate;
        long interestCents = Math.round(yearlyInterest);
        this.balance = Money.ofCents(currentCents + interestCents);
    }

    private void applyMaintenanceFee(double maintenanceFee) {
        long feeCents = Math.round(maintenanceFee * 100);
        if (this.balance.getAmountInCents() >= feeCents) {
            this.balance = this.balance.subtract(Money.ofCents(feeCents));
        }
    }

    private void printYearEndStatement(int year, double interestRate, double maintenanceFee) {
        long currentCents = this.balance.getAmountInCents();
        double yearlyInterest = currentCents * interestRate;
        String statement = "Year-End Statement for " + year + "\n";
        statement += "Account: " + this.accountId + "\n";
        statement += "Interest Applied: $" + String.format("%.2f", yearlyInterest / 100) + "\n";
        statement += "Maintenance Fee: $" + String.format("%.2f", maintenanceFee) + "\n";
        statement += "Final Balance: " + this.balance + "\n";
        System.out.println(statement);
    }

    // ==================================
    // Tax Report (Long Method → Extract Method)
    // ==================================

    public String calculateTaxReport(int year, double taxRate) {
        StringBuilder report = new StringBuilder();
        appendTaxReportHeader(report, year);
        appendTaxReportAccountInfo(report);
        appendTaxReportFinancials(report, taxRate);
        appendTaxReportFooter(report);
        return report.toString();
    }

    private void appendTaxReportHeader(StringBuilder report, int year) {
        report.append("Tax Report for Year: ").append(year).append("\n");
    }

    private void appendTaxReportAccountInfo(StringBuilder report) {
        report.append("Account: ").append(this.accountId).append("\n");
        report.append("Holder: ").append(this.accountHolder).append("\n");
    }

    private void appendTaxReportFinancials(StringBuilder report, double taxRate) {
        long balanceCents = this.balance.getAmountInCents();
        double balanceDollars = balanceCents / 100.0;
        report.append("Current Balance: $").append(String.format("%.2f", balanceDollars)).append("\n");

        double estimatedInterest = balanceDollars * 0.02;
        report.append("Estimated Interest Earned: $").append(String.format("%.2f", estimatedInterest)).append("\n");

        double taxOwed = estimatedInterest * taxRate;
        report.append("Tax Rate: ").append(String.format("%.2f%%", taxRate * 100)).append("\n");
        report.append("Estimated Tax Owed: $").append(String.format("%.2f", taxOwed)).append("\n");
    }

    private void appendTaxReportFooter(StringBuilder report) {
        report.append("Report Generated: ").append(java.time.LocalDate.now()).append("\n");
    }

    // ==================================
    // Withdraw and Transfer (unchanged, already short)
    // ==================================

    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.subtract(amount);
    }

    public void transferTo(BankAccountRefactored otherAccount, Money amount) {
        Objects.requireNonNull(otherAccount, "Destination account must not be null.");
        this.withdraw(amount);
        otherAccount.deposit(amount);
    }

    public Money withdrawAll() {
        Money amount = this.balance;
        this.balance = Money.ofCents(0);
        return amount;
    }

    // ==================================
    // Monthly Interest (Long Method → Extract Method + Decomposed Branch)
    // ==================================

    public void applyMonthlyInterest(double interestRate, boolean compoundDaily, int daysInMonth) {
        validateInterestRate(interestRate);
        long currentCents = this.balance.getAmountInCents();
        double interestAmount = calculateInterestAmount(currentCents, interestRate, compoundDaily, daysInMonth);
        long interestCents = Math.round(interestAmount);
        this.balance = Money.ofCents(currentCents + interestCents);
    }

    private void validateInterestRate(double interestRate) {
        if (interestRate < 0 || interestRate > 1) {
            throw new IllegalArgumentException("Interest rate must be between 0 and 1");
        }
    }

    private double calculateInterestAmount(long currentCents, double interestRate, boolean compoundDaily, int daysInMonth) {
        if (compoundDaily) {
            double dailyRate = interestRate / 365.0;
            double compoundFactor = Math.pow(1 + dailyRate, daysInMonth);
            return currentCents * (compoundFactor - 1);
        } else {
            double monthlyRate = interestRate / 12.0;
            return currentCents * monthlyRate;
        }
    }

    // ==================================
    // Getters and toString (unchanged)
    // ==================================

    public Money getBalance() {
        return this.balance;
    }

    public AccountHolder getAccountHolder() {
        return this.accountHolder;
    }

    public AccountID getAccountId() {
        return this.accountId;
    }

    @Override
    public String toString() {
        return "BankAccount[accountId=" + accountId +
               ", accountHolder=" + accountHolder +
               ", balance=" + balance + " cents]";
    }
}
