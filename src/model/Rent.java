package model;

import java.time.LocalDateTime;

public class Rent {
    private final String local;
    private final LocalDateTime localDateTime;
    private final String clientFiscalDocument;
    private final String vehicleLicensePlate;

    public Rent(String local, String clientFiscalDocument, String vehicleLicensePlate) {
        this.local = local;
        this.localDateTime = LocalDateTime.now();
        this.clientFiscalDocument = clientFiscalDocument;
        this.vehicleLicensePlate = vehicleLicensePlate;
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

    public String getVehicleLicensePlate() {
        return vehicleLicensePlate;
    }
}
