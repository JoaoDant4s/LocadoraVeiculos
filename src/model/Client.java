package model;

public class Client {
    private final String name;
    private final ClientType type;
    private final String fiscalDocument;

    public Client(String name, String fiscalDocument, ClientType type) {
        this.name = name;
        this.fiscalDocument = fiscalDocument;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getFiscalDocument() {
        return fiscalDocument;
    }

    public ClientType getType() {
        return type;
    }

}
