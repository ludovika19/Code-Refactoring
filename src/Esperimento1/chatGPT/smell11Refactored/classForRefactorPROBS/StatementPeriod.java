package Esperimento1.chatGPT.smell11Refactored.classForRefactorPROBS;


import java.util.Objects;

public final class StatementPeriod {

    private final String startDate;
    private final String endDate;

    public StatementPeriod(String startDate, String endDate) {
        // In a real system these would likely be proper date types,
        // but we stay with String here to avoid assumptions beyond the given code.
        this.startDate = Objects.requireNonNull(startDate, "Start date must not be null.");
        this.endDate = Objects.requireNonNull(endDate, "End date must not be null.");
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String format() {
        return startDate + " to " + endDate;
    }
}
