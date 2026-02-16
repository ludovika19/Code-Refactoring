package Esperimento2.Claude.smell7Refactored;


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

    // REFACTORED METHOD 1: generateAccountStatement
    // Applied: Extract Method
    public String generateAccountStatement(String startDate, String endDate) {
        StringBuilder statement = new StringBuilder();
        appendStatementHeader(statement, startDate, endDate);
        appendAccountDetails(statement);
        appendStatementFooter(statement);
        return statement.toString();
    }

    private void appendStatementHeader(StringBuilder statement, String startDate, String endDate) {
        statement.append("=== ACCOUNT STATEMENT ===\n");
        statement.append("Account ID: ").append(this.accountId).append("\n");
        statement.append("Account Holder: ").append(this.accountHolder).append("\n");
        statement.append("Statement Period: ").append(startDate).append(" to ").append(endDate).append("\n");
        statement.append("Current Balance: ").append(this.balance).append("\n");
        statement.append("------------------------\n");
    }

    private void appendAccountDetails(StringBuilder statement) {
        statement.append("Account Type: Standard\n");
        statement.append("Interest Rate: 0.00%\n");
        statement.append("Monthly Fee: $0.00\n");
        statement.append("Overdraft Protection: No\n");
    }

    private void appendStatementFooter(StringBuilder statement) {
        statement.append("========================\n");
    }

    // REFACTORED METHOD 2: validateAccountForLoan
    // Applied: Extract Method + Decompose Conditional
    public boolean validateAccountForLoan(Money loanAmount, int creditScore, int yearsOfHistory) {
        if (!isValidCreditScore(creditScore)) {
            return false;
        }
        if (!isValidYearsOfHistory(yearsOfHistory)) {
            return false;
        }
        if (!hasSufficientBalance(loanAmount)) {
            return false;
        }
        if (!meetsCreditHistoryRequirements(creditScore, yearsOfHistory)) {
            return false;
        }
        if (!meetsLargeLoanRequirements(loanAmount, creditScore)) {
            return false;
        }
        return true;
    }

    private boolean isValidCreditScore(int creditScore) {
        return creditScore >= 300 && creditScore <= 850;
    }

    private boolean isValidYearsOfHistory(int yearsOfHistory) {
        return yearsOfHistory >= 0;
    }

    private boolean hasSufficientBalance(Money loanAmount) {
        Money minimumBalance = Money.ofCents(loanAmount.getAmountInCents() / 10);
        return this.balance.getAmountInCents() >= minimumBalance.getAmountInCents();
    }

    private boolean meetsCreditHistoryRequirements(int creditScore, int yearsOfHistory) {
        if (creditScore < 600 && yearsOfHistory < 2) {
            return false;
        }
        if (creditScore >= 600 && creditScore < 700 && yearsOfHistory < 1) {
            return false;
        }
        return true;
    }

    private boolean meetsLargeLoanRequirements(Money loanAmount, int creditScore) {
        return loanAmount.getAmountInCents() <= 100000000 || creditScore >= 750;
    }

    // REFACTORED METHOD 3: performEndOfYearProcessing
    // Applied: Extract Method + Replace Temp with Query
    public void performEndOfYearProcessing(int year, double interestRate, double maintenanceFee, boolean sendStatement) {
        applyYearlyInterest(interestRate);
        deductMaintenanceFee(maintenanceFee);
        if (sendStatement) {
            printYearEndStatement(year, interestRate, maintenanceFee);
        }
    }

    private void applyYearlyInterest(double interestRate) {
        long interestCents = calculateYearlyInterestInCents(interestRate);
        this.balance = Money.ofCents(this.balance.getAmountInCents() + interestCents);
    }

    private long calculateYearlyInterestInCents(double interestRate) {
        long currentCents = this.balance.getAmountInCents();
        double yearlyInterest = currentCents * interestRate;
        return Math.round(yearlyInterest);
    }

    private void deductMaintenanceFee(double maintenanceFee) {
        long feeCents = Math.round(maintenanceFee * 100);
        if (this.balance.getAmountInCents() >= feeCents) {
            this.balance = this.balance.subtract(Money.ofCents(feeCents));
        }
    }

    private void printYearEndStatement(int year, double interestRate, double maintenanceFee) {
        String statement = buildYearEndStatement(year, interestRate, maintenanceFee);
        System.out.println(statement);
    }

    private String buildYearEndStatement(int year, double interestRate, double maintenanceFee) {
        long currentCents = this.balance.getAmountInCents();
        double yearlyInterest = currentCents * interestRate;
        
        String statement = "Year-End Statement for " + year + "\n";
        statement += "Account: " + this.accountId + "\n";
        statement += "Interest Applied: $" + String.format("%.2f", yearlyInterest / 100) + "\n";
        statement += "Maintenance Fee: $" + String.format("%.2f", maintenanceFee) + "\n";
        statement += "Final Balance: " + this.balance + "\n";
        return statement;
    }

    // REFACTORED METHOD 4: calculateTaxReport
    // Applied: Extract Method + Replace Temp with Query
    public String calculateTaxReport(int year, double taxRate) {
        StringBuilder report = new StringBuilder();
        appendTaxReportHeader(report, year);
        appendBalanceInformation(report);
        appendTaxCalculations(report, taxRate);
        appendReportGenerationDate(report);
        return report.toString();
    }

    private void appendTaxReportHeader(StringBuilder report, int year) {
        report.append("Tax Report for Year: ").append(year).append("\n");
        report.append("Account: ").append(this.accountId).append("\n");
        report.append("Holder: ").append(this.accountHolder).append("\n");
    }

    private void appendBalanceInformation(StringBuilder report) {
        double balanceDollars = getBalanceInDollars();
        report.append("Current Balance: $").append(String.format("%.2f", balanceDollars)).append("\n");
    }

    private void appendTaxCalculations(StringBuilder report, double taxRate) {
        double balanceDollars = getBalanceInDollars();
        double estimatedInterest = calculateEstimatedInterest(balanceDollars);
        double taxOwed = estimatedInterest * taxRate;
        
        report.append("Estimated Interest Earned: $").append(String.format("%.2f", estimatedInterest)).append("\n");
        report.append("Tax Rate: ").append(String.format("%.2f%%", taxRate * 100)).append("\n");
        report.append("Estimated Tax Owed: $").append(String.format("%.2f", taxOwed)).append("\n");
    }

    private void appendReportGenerationDate(StringBuilder report) {
        report.append("Report Generated: ").append(java.time.LocalDate.now()).append("\n");
    }

    private double getBalanceInDollars() {
        return this.balance.getAmountInCents() / 100.0;
    }

    private double calculateEstimatedInterest(double balanceDollars) {
        return balanceDollars * 0.02;
    }

    // REFACTORED METHOD 5: applyMonthlyInterest
    // Applied: Extract Method + Decompose Conditional
    public void applyMonthlyInterest(double interestRate, boolean compoundDaily, int daysInMonth) {
        validateInterestRate(interestRate);
        long interestCents = calculateMonthlyInterestInCents(interestRate, compoundDaily, daysInMonth);
        this.balance = Money.ofCents(this.balance.getAmountInCents() + interestCents);
    }

    private void validateInterestRate(double interestRate) {
        if (interestRate < 0 || interestRate > 1) {
            throw new IllegalArgumentException("Interest rate must be between 0 and 1");
        }
    }

    private long calculateMonthlyInterestInCents(double interestRate, boolean compoundDaily, int daysInMonth) {
        long currentCents = this.balance.getAmountInCents();
        double interestAmount;
        
        if (compoundDaily) {
            interestAmount = calculateCompoundDailyInterest(currentCents, interestRate, daysInMonth);
        } else {
            interestAmount = calculateSimpleMonthlyInterest(currentCents, interestRate);
        }
        
        return Math.round(interestAmount);
    }

    private double calculateCompoundDailyInterest(long currentCents, double interestRate, int daysInMonth) {
        double dailyRate = interestRate / 365.0;
        double compoundFactor = Math.pow(1 + dailyRate, daysInMonth);
        return currentCents * (compoundFactor - 1);
    }

    private double calculateSimpleMonthlyInterest(long currentCents, double interestRate) {
        double monthlyRate = interestRate / 12.0;
        return currentCents * monthlyRate;
    }

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