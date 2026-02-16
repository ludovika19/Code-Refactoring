package Esperimento2.Gemini.smell2Refactored.classForRefactorDC;

import java.util.Objects;

public class IdentityDocument {
    private final String documentType;
    private final String documentNumber;
    private final String issuingCountry;

    public IdentityDocument(String documentType, String documentNumber, String issuingCountry) {
        this.documentType = Objects.requireNonNull(documentType);
        this.documentNumber = Objects.requireNonNull(documentNumber);
        this.issuingCountry = Objects.requireNonNull(issuingCountry);
    }

    public boolean isValid() {
        return documentType != null && documentNumber != null && issuingCountry != null;
    }

    // Getters
}