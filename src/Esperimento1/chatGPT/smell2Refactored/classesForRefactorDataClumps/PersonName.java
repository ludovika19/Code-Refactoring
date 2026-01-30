package Esperimento1.chatGPT.smell2Refactored.classesForRefactorDataClumps;

import java.util.Objects;

public class PersonName {

    private final String firstName;
    private final String middleName;
    private final String lastName;

    public PersonName(String firstName, String middleName, String lastName) {
        this.firstName = Objects.requireNonNull(firstName, "First name must not be null.");
        this.middleName = Objects.requireNonNull(middleName, "Middle name must not be null.");
        this.lastName = Objects.requireNonNull(lastName, "Last name must not be null.");
    }

    public String firstName() {
        return firstName;
    }

    public String middleName() {
        return middleName;
    }

    public String lastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + middleName + " " + lastName;
    }
}