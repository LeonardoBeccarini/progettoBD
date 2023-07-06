package model.domain;

public enum Stato {
    APERTO("aperto"),
    FINALIZZATO("finalizzato"),
    SPEDITO("spedito"),
    CONSEGNATO("consegnato");
    private final String id;

    private Stato(String id) {
        this.id = id;
    }
    public static Stato fromString(String id) {
        for (Stato type : values()) {
            if (type.getId().equals(id)) {
                return type;
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }
}

