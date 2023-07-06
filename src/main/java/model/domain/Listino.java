package model.domain;

import java.sql.Date;

public class Listino {
    private String pianta;
    private float prezzo;
    private Date dataInizio;
    private Date dataFine;

    public Listino(String pianta, float prezzo) {
        this.pianta = pianta;
        this.prezzo = prezzo;
    }
    public Listino(String pianta, Date dataFine){
        this.pianta = pianta;
        this.dataFine = dataFine;
    }/*
    public Listino(String pianta, Date dataInizio, float prezzo){
        this.pianta = pianta;
        this.dataInizio = dataInizio;
        this.prezzo = prezzo;
    }*/
    public Listino(String pianta, Date dataInizio, Date dataFine, float prezzo){
        this.pianta = pianta;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.prezzo = prezzo;
    }


    public Date getDataInizio() {
        return dataInizio;
    }

    public String getPianta() {
        return pianta;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public Date getDataFine() {
        return dataFine;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(pianta).append(": ").append(prezzo).append(" valido da ").append(dataInizio).append(" a ").append(dataFine).append("): \n");
        return sb.toString();
    }
}
