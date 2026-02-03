package Esperimento1.Gemini.smell7Refactored;


import java.time.LocalDate;
import java.util.Objects;
import utility.*;

public class BankAccount {

    private final AccountHolder accountHolder;
    private final AccountID accountId;
    private Money balance;

    public BankAccount(AccountHolder accountHolder, AccountID accountId) {
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

    /**
     * Refactored from generateAccountStatement.
     * The original method was too long, combining header, body, and footer generation.
     * It has been broken down into smaller, more focused methods.
     */
    public String generateAccountStatement(String startDate, String endDate) {
        StringBuilder statement = new StringBuilder();
        appendStatementHeader(statement, startDate, endDate);
        appendStatementBody(statement);
        appendStatementFooter(statement);
        return statement.toString();
    }

    private void appendStatementHeader(StringBuilder statement, String startDate, String endDate) {
        statement.append("=== ACCOUNT STATEMENT ===\n");
        statement.append("Account ID: ").append(this.accountId).append("\n");
        statement.append("Account Holder: ").append(this.accountHolder).append("\n");
        statement.append("Statement Period: ").append(startDate).append(" to ").append(endDate).append("\n");
        statement.append("Current Balance: ").append(this.balance).append("\n");
    }

    private void appendStatementBody(StringBuilder statement) {
        statement.append("------------------------\n");
        statement.append("Account Type: Standard\n");
        statement.append("Interest Rate: 0.00%\n");
        statement.append("Monthly Fee: $0.00\n");
        statement.append("Overdraft Protection: No\n");
    }

    private void appendStatementFooter(StringBuilder statement) {
        statement.append("========================\n");
    }

    /**
     * Refactored from validateAccountForLoan.
     * The original method had multiple conditional checks, making it complex.
     * It's now broken down into smaller validation methods for clarity.
     */
    public boolean validateAccountForLoan(Money loanAmount, int creditScore, int yearsOfHistory) {
        return isCreditScoreValid(creditScore) &&
               isYearsOfHistoryValid(yearsOfHistory) &&
               hasSufficientBalanceForLoan(loanAmount) &&
               meetsCreditHistoryRequirements(creditScore, yearsOfHistory) &&
               meetsLoanAmountCreditLimit(loanAmount, creditScore);
    }

    private boolean isCreditScoreValid(int creditScore) {
        return creditScore >= 300 && creditScore <= 850;
    }

    private boolean isYearsOfHistoryValid(int yearsOfHistory) {
        return yearsOfHistory >= 0;
    }

    private boolean hasSufficientBalanceForLoan(Money loanAmount) {
        Money minimumBalance = Money.ofCents(loanAmount.getAmountInCents() / 10);
        return this.balance.isGreaterThanOrEqual(minimumBalance);
    }

    private boolean meetsCreditHistoryRequirements(int creditScore, int yearsOfHistory) {
        if (creditScore < 600) {
            return yearsOfHistory >= 2;
        }
        if (creditScore < 700) {
            return yearsOfHistory >= 1;
        }
        return true;
    }

    private boolean meetsLoanAmountCreditLimit(Money loanAmount, int creditScore) {
        if (loanAmount.getAmountInCents() > 100000000) { // $1,000,000
            return creditScore >= 750;
        }
        return true;
    }

    /**
     * Refactored from performEndOfYearProcessing.
     * The method handled interest calculation, fee deduction, and statement generation.
     * These responsibilities have been extracted into separate methods.
     */
    public void performEndOfYearProcessing(int year, double interestRate, double maintenanceFee) {
        applyYearlyInterest(interestRate);
        deductMaintenanceFee(maintenanceFee);
        generateAndSendYearEndStatement(year, interestRate, maintenanceFee);
    }

    private void applyYearlyInterest(double interestRate) {
        long interestCents = Math.round(this.balance.getAmountInCents() * interestRate);
        this.balance = this.balance.add(Money.ofCents(interestCents));
    }

    private void deductMaintenanceFee(double maintenanceFee) {
        long feeCents = Math.round(maintenanceFee * 100);
        Money feeAmount = Money.ofCents(feeCents);
        if (this.balance.isGreaterThanOrEqual(feeAmount)) {
            this.balance = this.balance.subtract(feeAmount);
        }
    }
    
    private void generateAndSendYearEndStatement(int year, double interestRate, double maintenanceFee) {
        double yearlyInterest = this.balance.getAmountInCents() * interestRate;
        String statement = "Year-End Statement for " + year + "\n" +
                           "Account: " + this.accountId + "\n" +
                           "Interest Applied: $" + String.format("%.2f", yearlyInterest / 100) + "\n" +
                           "Maintenance Fee: $" + String.format("%.2f", maintenanceFee) + "\n" +
                           "Final Balance: " + this.balance + "\n";
        System.out.println(statement);
    }

    /**
     * Refactored from calculateTaxReport.
     * The original method was building a large string with various calculations.
     * Logic has been broken down for better readability and separation of concerns.
     */
    public String calculateTaxReport(int year, double taxRate) {
        StringBuilder report = new StringBuilder();
        appendTaxReportHeader(report, year);
        appendTaxReportBody(report, taxRate);
        appendTaxReportFooter(report);
        return report.toString();
    }

    private void appendTaxReportHeader(StringBuilder report, int year) {
        report.append("Tax Report for Year: ").append(year).append("\n");
        report.append("Account: ").append(this.accountId).append("\n");
        report.append("Holder: ").append(this.accountHolder).append("\n");
    }

    private void appendTaxReportBody(StringBuilder report, double taxRate) {
        double balanceDollars = this.balance.getAmountInCents() / 100.0;
        report.append("Current Balance: $").append(String.format("%.2f", balanceDollars)).append("\n");

        double estimatedInterest = balanceDollars * 0.02; // Assuming a fixed 2% for estimation
        report.append("Estimated Interest Earned: $").append(String.format("%.2f", estimatedInterest)).append("\n");

        double taxOwed = estimatedInterest * taxRate;
        report.append("Tax Rate: ").append(String.format("%.2f%%", taxRate * 100)).append("\n");
        report.append("Estimated Tax Owed: $").append(String.format("%.2f", taxOwed)).append("\n");
    }

    private void appendTaxReportFooter(StringBuilder report) {
        report.append("Report Generated: ").append(LocalDate.now()).append("\n");
    }
    
    public void withdraw(Money amount) {
        validatePositiveAmount(amount);
        this.balance = this.balance.subtract(amount);
    }

    public void transferTo(BankAccount otherAccount, Money amount) {
        Objects.requireNonNull(otherAccount, "Destination account must not be null.");
        this.withdraw(amount);
        otherAccount.deposit(amount);
    }

    public Money withdrawAll() {
        Money amount = this.balance;
        this.balance = Money.ofCents(0);
        return amount;
    }

    /**
     * Refactored from applyMonthlyInterest.
     * The method contained complex conditional logic for interest calculation.
     * It has been simplified by extracting the different calculation strategies into their own methods.
     */
    public void applyMonthlyInterest(double interestRate, boolean compoundDaily, int daysInMonth) {
        if (interestRate < 0 || interestRate > 1) {
            throw new IllegalArgumentException("Interest rate must be between 0 and 1");
        }
        long interestCents = compoundDaily ?
            calculateDailyCompoundedInterest(interestRate, daysInMonth) :
            calculateSimpleMonthlyInterest(interestRate);

        this.balance = this.balance.add(Money.ofCents(interestCents));
    }

    private long calculateDailyCompoundedInterest(double annualRate, int daysInMonth) {
        double dailyRate = annualRate / 365.0;
        double compoundFactor = Math.pow(1 + dailyRate, daysInMonth);
        double interestAmount = this.balance.getAmountInCents() * (compoundFactor - 1);
        return Math.round(interestAmount);
    }

    private long calculateSimpleMonthlyInterest(double annualRate) {
        double monthlyRate = annualRate / 12.0;
        double interestAmount = this.balance.getAmountInCents() * monthlyRate;
        return Math.round(interestAmount);
    }

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
               ", balance=" + balance + "]";
    }
}
