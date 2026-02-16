package Esperimento2.Gemini.smell2Refactored.classForRefactorDC;

import java.util.Objects;

public class FullName {
    private final String firstName;
    private final String middleName;
    private final String lastName;

    public FullName(String firstName, String lastName, String middleName) {
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.middleName = middleName; // Middle name can be optional
    }

    @Override
    public String toString() {
        return firstName + (middleName != null ? " " + middleName : "") + " " + lastName;
    }

    // Getters
}