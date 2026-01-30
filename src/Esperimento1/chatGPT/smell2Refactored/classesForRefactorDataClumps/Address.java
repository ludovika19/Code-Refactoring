package Esperimento1.chatGPT.smell2Refactored.classesForRefactorDataClumps;

import java.util.Objects;

public class Address {

    private final String street;
    private final String city;
    private final String zipCode;

    public Address(String street, String city, String zipCode) {
        this.street = Objects.requireNonNull(street, "Street must not be null.");
        this.city = Objects.requireNonNull(city, "City must not be null.");
        this.zipCode = Objects.requireNonNull(zipCode, "Zip code must not be null.");
    }

    public String street() {
        return street;
    }

    public String city() {
        return city;
    }

    public String zipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + zipCode;
    }
}
