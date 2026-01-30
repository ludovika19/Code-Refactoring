package Esperimento1.Gemini.smell2Refactored.classesForRefactorDataClumps;

public class IdentityDocument {
    private final String documentType;
    private final String documentNumber;
    private final String issuingCountry;

    public IdentityDocument(String documentType, String documentNumber, String issuingCountry) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.issuingCountry = issuingCountry;
    }

    public boolean isValid() {
        return documentType != null && documentNumber != null && issuingCountry != null;
    }
}
