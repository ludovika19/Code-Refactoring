package Esperimento2.Gemini.smell2Refactored.classForRefactorDC;

import java.util.Objects;

public class Address {
    private final String street;
    private final String city;
    private final String zipCode;

    public Address(String street, String city, String zipCode) {
        this.street = Objects.requireNonNull(street);
        this.city = Objects.requireNonNull(city);
        this.zipCode = Objects.requireNonNull(zipCode);
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + zipCode;
    }

    // Getters for street, city, and zipCode if needed
}