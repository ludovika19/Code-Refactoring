package Esperimento1.ClaudeSonet.smell2Refactored.classForRefactorDataClumps;

import java.util.Objects;

public class IdentityDocument {
    private final String documentType;
    private final String documentNumber;
    private final String issuingCountry;

    public IdentityDocument(String documentType, String documentNumber, String issuingCountry) {
        this.documentType = Objects.requireNonNull(documentType, "Document type must not be null.");
        this.documentNumber = Objects.requireNonNull(documentNumber, "Document number must not be null.");
        this.issuingCountry = Objects.requireNonNull(issuingCountry, "Issuing country must not be null.");
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public boolean isValid() {
        return documentType != null && documentNumber != null && issuingCountry != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentityDocument that = (IdentityDocument) o;
        return Objects.equals(documentType, that.documentType) &&
               Objects.equals(documentNumber, that.documentNumber) &&
               Objects.equals(issuingCountry, that.issuingCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentType, documentNumber, issuingCountry);
    }
}