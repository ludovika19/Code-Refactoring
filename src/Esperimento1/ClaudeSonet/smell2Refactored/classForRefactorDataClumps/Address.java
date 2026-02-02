package Esperimento1.ClaudeSonet.smell2Refactored.classForRefactorDataClumps;

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

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
               Objects.equals(city, address.city) &&
               Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, zipCode);
    }
}