package Esperimento1.Gemini.smell2Refactored.classesForRefactorDataClumps;


public class FullName {
    private final String firstName;
    private final String lastName;
    private final String middleName;

    public FullName(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    @Override
    public String toString() {
        return firstName + " " + middleName + " " + lastName;
    }
}
