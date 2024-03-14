package model;

import java.time.LocalDateTime;

public class Rent {
    private final String local;
    private final LocalDateTime localDateTime;

    public Rent(String local) {
        this.local = local;
        this.localDateTime = LocalDateTime.now();
    }
}
