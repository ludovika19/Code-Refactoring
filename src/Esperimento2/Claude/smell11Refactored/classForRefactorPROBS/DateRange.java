package Esperimento2.Claude.smell11Refactored.classForRefactorPROBS;


import java.util.Objects;

public class DateRange {
    private final String startDate;
    private final String endDate;

    public DateRange(String startDate, String endDate) {
        this.startDate = Objects.requireNonNull(startDate, "Start date must not be null.");
        this.endDate = Objects.requireNonNull(endDate, "End date must not be null.");
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return startDate + " to " + endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateRange dateRange = (DateRange) o;
        return Objects.equals(startDate, dateRange.startDate) &&
               Objects.equals(endDate, dateRange.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
