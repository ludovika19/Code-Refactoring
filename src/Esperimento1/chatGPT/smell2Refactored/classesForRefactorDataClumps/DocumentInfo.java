package Esperimento1.chatGPT.smell2Refactored.classesForRefactorDataClumps;

import java.util.Objects;

public class DocumentInfo {

    private final String documentType;
    private final String documentNumber;
    private final String issuingCountry;

    public DocumentInfo(String documentType, String documentNumber, String issuingCountry) {
        this.documentType = Objects.requireNonNull(documentType, "Document type must not be null.");
        this.documentNumber = Objects.requireNonNull(documentNumber, "Document number must not be null.");
        this.issuingCountry = Objects.requireNonNull(issuingCountry, "Issuing country must not be null.");
    }

    public String documentType() {
        return documentType;
    }

    public String documentNumber() {
        return documentNumber;
    }

    public String issuingCountry() {
        return issuingCountry;
    }
}
