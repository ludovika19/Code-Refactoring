package Esperimento2.Gemini.smell11Refactored.classrorrefactorprobs;


import java.util.Objects;

public class StatementPeriod {
    private final String startDate;
    private final String endDate;

    public StatementPeriod(String startDate, String endDate) {
        this.startDate = Objects.requireNonNull(startDate, "Start date must not be null.");
        this.endDate = Objects.requireNonNull(endDate, "End date must not be null.");
    }

    @Override
    public String toString() {
        return startDate + " to " + endDate;
    }
}
