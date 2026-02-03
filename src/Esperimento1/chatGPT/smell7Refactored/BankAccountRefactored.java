package Esperimento1.chatGPT.smell7Refactored;


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

    // Line 29: was generateAccountStatement(...) – Extract Method applied
    public String generateAccountStatement(String startDate, String endDate) {
        StringBuilder statement = new StringBuilder();
        appendAccountHeader(statement, startDate, endDate);
        appendAccountDetails(statement);
        return statement.toString();
    }

    private void appendAccountHeader(StringBuilder statement, String startDate, String endDate) {
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
        statement.append("========================\n");
    }

    // Line 45: was validateAccountForLoan(...) – Extract Method + Extract Variable
    public boolean validateAccountForLoan(Money loanAmount, int creditScore, int yearsOfHistory) {
        if (!isCreditScoreInValidRange(creditScore)) {
            return false;
        }
        if (!hasNonNegativeHistory(yearsOfHistory)) {
            return false;
        }

        Money minimumBalance = calculateMinimumBalanceForLoan(loanAmount);
        if (!hasSufficientBalance(minimumBalance)) {
            return false;
        }

        if (isVeryLowCreditAndShortHistory(creditScore, yearsOfHistory)) {
            return false;
        }

        if (isMediumCreditWithInsufficientHistory(creditScore, yearsOfHistory)) {
            return false;
        }

        if (isHighLoanAmountWithInsufficientCredit(loanAmount, creditScore)) {
            return false;
        }

        return true;
    }

    private boolean isCreditScoreInValidRange(int creditScore) {
        return creditScore >= 300 && creditScore <= 850;
    }

    private boolean hasNonNegativeHistory(int yearsOfHistory) {
        return yearsOfHistory >= 0;
    }

    private Money calculateMinimumBalanceForLoan(Money loanAmount) {
        long minimumBalanceCents = loanAmount.getAmountInCents() / 10;
        return Money.ofCents(minimumBalanceCents);
    }

    private boolean hasSufficientBalance(Money minimumBalance) {
        return this.balance.getAmountInCents() >= minimumBalance.getAmountInCents();
    }

    private boolean isVeryLowCreditAndShortHistory(int creditScore, int yearsOfHistory) {
        return creditScore < 600 && yearsOfHistory < 2;
    }

    private boolean isMediumCreditWithInsufficientHistory(int creditScore, int yearsOfHistory) {
        return creditScore >= 600 && creditScore < 700 && yearsOfHistory < 1;
    }

    private boolean isHighLoanAmountWithInsufficientCredit(Money loanAmount, int creditScore) {
        return loanAmount.getAmountInCents() > 100000000 && creditScore < 750;
    }

    // Line 68: was performEndOfYearProcessing(...) – Extract Method
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
        double yearlyInterest = currentCents * interestRate; // explanatory calc for display
        String statement = "Year-End Statement for " + year + "\n";
        statement += "Account: " + this.accountId + "\n";
        statement += "Interest Applied: $" + String.format("%.2f", yearlyInterest / 100) + "\n";
        statement += "Maintenance Fee: $" + String.format("%.2f", maintenanceFee) + "\n";
        statement += "Final Balance: " + this.balance + "\n";
        System.out.println(statement);
    }

    // Line 87: was calculateTaxReport(...) – Extract Method + Introduce Explaining Variable
    public String calculateTaxReport(int year, double taxRate) {
        StringBuilder report = new StringBuilder();
        appendTaxReportHeader(report, year);
        double balanceDollars = calculateBalanceInDollars();
        appendTaxReportBalance(report, balanceDollars);
        double estimatedInterest = estimateInterest(balanceDollars);
        double taxOwed = calculateTaxOwed(estimatedInterest, taxRate);
        appendTaxReportDetails(report, taxRate, estimatedInterest, taxOwed);
        appendTaxReportFooter(report);
        return report.toString();
    }

    private void appendTaxReportHeader(StringBuilder report, int year) {
        report.append("Tax Report for Year: ").append(year).append("\n");
        report.append("Account: ").append(this.accountId).append("\n");
        report.append("Holder: ").append(this.accountHolder).append("\n");
    }

    private double calculateBalanceInDollars() {
        long balanceCents = this.balance.getAmountInCents();
        return balanceCents / 100.0;
    }

    private void appendTaxReportBalance(StringBuilder report, double balanceDollars) {
        report.append("Current Balance: $").append(String.format("%.2f", balanceDollars)).append("\n");
    }

    private double estimateInterest(double balanceDollars) {
        return balanceDollars * 0.02;
    }

    private double calculateTaxOwed(double estimatedInterest, double taxRate) {
        return estimatedInterest * taxRate;
    }

    private void appendTaxReportDetails(StringBuilder report, double taxRate, double estimatedInterest, double taxOwed) {
        report.append("Estimated Interest Earned: $").append(String.format("%.2f", estimatedInterest)).append("\n");
        report.append("Tax Rate: ").append(String.format("%.2f%%", taxRate * 100)).append("\n");
        report.append("Estimated Tax Owed: $").append(String.format("%.2f", taxOwed)).append("\n");
    }

    private void appendTaxReportFooter(StringBuilder report) {
        report.append("Report Generated: ").append(java.time.LocalDate.now()).append("\n");
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

    // Line 122: was applyMonthlyInterest(...) – Extract Method + Introduce Explaining Variable
    public void applyMonthlyInterest(double interestRate, boolean compoundDaily, int daysInMonth) {
        validateInterestRateRange(interestRate);
        long currentCents = this.balance.getAmountInCents();
        double interestAmount = calculateMonthlyInterestAmount(interestRate, compoundDaily, daysInMonth, currentCents);
        applyInterestAmount(currentCents, interestAmount);
    }

    private void validateInterestRateRange(double interestRate) {
        if (interestRate < 0 || interestRate > 1) {
            throw new IllegalArgumentException("Interest rate must be between 0 and 1");
        }
    }

    private double calculateMonthlyInterestAmount(double interestRate, boolean compoundDaily, int daysInMonth, long currentCents) {
        if (compoundDaily) {
            return calculateDailyCompoundedInterest(interestRate, daysInMonth, currentCents);
        } else {
            return calculateSimpleMonthlyInterest(interestRate, currentCents);
        }
    }

    private double calculateDailyCompoundedInterest(double interestRate, int daysInMonth, long currentCents) {
        double dailyRate = interestRate / 365.0;
        double compoundFactor = Math.pow(1 + dailyRate, daysInMonth);
        return currentCents * (compoundFactor - 1);
    }

    private double calculateSimpleMonthlyInterest(double interestRate, long currentCents) {
        double monthlyRate = interestRate / 12.0;
        return currentCents * monthlyRate;
    }

    private void applyInterestAmount(long currentCents, double interestAmount) {
        long interestCents = Math.round(interestAmount);
        this.balance = Money.ofCents(currentCents + interestCents);
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
