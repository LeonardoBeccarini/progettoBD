package model.domain;

public class Colorazione {
    private String pianta;
    private String colore;

    public Colorazione(String pianta, String colore) {
        this.pianta = pianta;
        this.colore = colore;
    }

    public String getPianta() {
        return pianta;
    }

    public String getColore() {
        return colore;
    }
}
