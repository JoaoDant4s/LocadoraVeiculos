package model;

import java.time.LocalDateTime;

public class Rent {
    private final String local;
    private final LocalDateTime localDateTime;
    private final String clientFiscalDocument;

    public Rent(String local, String clientFiscalDocument) {
        this.local = local;
        this.localDateTime = LocalDateTime.now();
        this.clientFiscalDocument = clientFiscalDocument;
    }

    public String getLocal() {
        return local;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getClientFiscalDocument() {
        return clientFiscalDocument;
    }
    
}
