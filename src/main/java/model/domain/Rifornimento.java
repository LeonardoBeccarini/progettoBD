package model.domain;

import java.sql.Date;

public class Rifornimento {
    private String fornitore;
    private String codicePianta;
    private int quantita;

    public Rifornimento(String fornitore, String codicePianta, int quantita) {
        this.fornitore = fornitore;
        this.codicePianta = codicePianta;
        this.quantita = quantita;
    }

    public String getFornitore() {
        return fornitore;
    }

    public String getCodicePianta() {
        return codicePianta;
    }

    public int getQuantita() {
        return quantita;
    }
}
