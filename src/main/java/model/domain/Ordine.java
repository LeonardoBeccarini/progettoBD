package model.domain;

import java.sql.Date;

public class Ordine {
    private int codiceOrdine;
    private String indirizzo;
    private Date data;
    private Stato stato;
    private String recapito;
    private String rivendita;

    public int getCodiceOrdine() {
        return codiceOrdine;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Date getData() {
        return data;
    }

    public Stato getStato() {
        return stato;
    }

    public String getRecapito() {
        return recapito;
    }

    public String getRivendita() {
        return rivendita;
    }

    public Ordine(String indirizzo, Date data, Stato stato, String recapito, String rivendita) {
        this.indirizzo = indirizzo;
        this.data = data;
        this.stato = stato;
        this.recapito = recapito;
        this.rivendita = rivendita;
    }

    public Ordine(int codiceOrdine, String indirizzo, Date data, Stato stato, String recapito, String rivendita) {
        this.codiceOrdine = codiceOrdine;
        this.indirizzo = indirizzo;
        this.data = data;
        this.stato = stato;
        this.recapito = recapito;
        this.rivendita = rivendita;
    }

    public Ordine(int codiceOrdine, String indirizzo, Date data, String recapito, String rivendita) {
        this.codiceOrdine = codiceOrdine;
        this.indirizzo = indirizzo;
        this.data = data;
        this.recapito = recapito;
        this.rivendita = rivendita;
    }

    public Ordine(int codiceOrdine, Stato stato) {
        this.codiceOrdine = codiceOrdine;
        this.stato = stato;
    }

    public Ordine(int codiceOrdine) {
        this.codiceOrdine = codiceOrdine;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(indirizzo).append("-").append(data).append('-').append(stato).append("-").append(recapito).append("-").append(rivendita).append("\n");
        return sb.toString();
    }
}
