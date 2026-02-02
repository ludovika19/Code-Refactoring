package Esperimento1.ClaudeSonet.smell2Refactored.classForRefactorDataClumps;

import java.util.Objects;

public class PersonName {
    private final String firstName;
    private final String lastName;
    private final String middleName;

    public PersonName(String firstName, String lastName, String middleName) {
        this.firstName = Objects.requireNonNull(firstName, "First name must not be null.");
        this.lastName = Objects.requireNonNull(lastName, "Last name must not be null.");
        this.middleName = middleName; // Middle name can be null
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFullName() {
        if (middleName != null && !middleName.isEmpty()) {
            return firstName + " " + middleName + " " + lastName;
        }
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonName that = (PersonName) o;
        return Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName) &&
               Objects.equals(middleName, that.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName);
    }
}
