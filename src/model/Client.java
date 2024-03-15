package model;

public class Client {
    private final String name;
    private final String fiscalDocument;

    public Client(String name, String fiscalDocument) {
        this.name = name;
        this.fiscalDocument = fiscalDocument;
    }

    public String getName() {
        return name;
    }

    public String getFiscalDocument() {
        return fiscalDocument;
    }
}
