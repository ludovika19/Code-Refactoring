package Esperimento1.ClaudeSonet.smell11Refactored.classForRefactorPROBS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateRange {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public DateRange(String startDate, String endDate) {
        this.startDate = LocalDate.parse(Objects.requireNonNull(startDate, "Start date must not be null."), FORMATTER);
        this.endDate = LocalDate.parse(Objects.requireNonNull(endDate, "End date must not be null."), FORMATTER);
        
        if (this.startDate.isAfter(this.endDate)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return startDate.format(FORMATTER) + " to " + endDate.format(FORMATTER);
    }
}
