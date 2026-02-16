package Esperimento2.chatGPT.smell2Refactored.classForRefactorDC;

import java.util.Objects;

public final class IdentityDocument {

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
}
