package model.domain;

public class Composizione {

    private String codiceSpecie;
    private String nomeSpecie;
    private int codiceOrdine;
    private int quantita;
    private float prezzoUnita;
    private float prezzoCumulativo;


    public Composizione(String codice, int quantita) {
        this.codiceSpecie = codice;
        this.quantita = quantita;
    }

    public Composizione(String codiceSpecie, int codiceOrdine, int quantita) {
        this.codiceSpecie = codiceSpecie;
        this.codiceOrdine = codiceOrdine;
        this.quantita = quantita;
    }

    public Composizione(String codice, String nomeSpecie, int quantita, float prezzoUnita, float prezzoCumulativo) {
        this.codiceSpecie = codice;
        this.nomeSpecie = nomeSpecie;
        this.quantita = quantita;
        this.prezzoUnita = prezzoUnita;
        this.prezzoCumulativo = prezzoCumulativo;
    }

    public String getCodiceSpecie() {
        return codiceSpecie;
    }

    public int getQuantita() {
        return quantita;
    }

    public int getCodiceOrdine() {
        return codiceOrdine;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(codiceSpecie).append(" ").append(nomeSpecie).append(' ').append(quantita).append(" ").append(prezzoUnita).append(" ").append(prezzoCumulativo).append("\n");
        return sb.toString();
    }
}
