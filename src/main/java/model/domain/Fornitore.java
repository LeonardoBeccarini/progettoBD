package model.domain;

import java.util.ArrayList;
import java.util.List;

public class Fornitore {
    private String Codice;
    private  String nome;
    private  String codiceFiscale;

    private final List<Indirizzo> indirizzi = new ArrayList<>();

    public Fornitore(String codiceFiscale, String nome) {
        this.nome = nome;
        this.codiceFiscale = codiceFiscale;
    }

    public Fornitore(String codice) {
        Codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public String getCodice() {
        return Codice;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nome).append(" - ").append(codiceFiscale).append("): \n");
        for(Indirizzo i: indirizzi) {
            sb.append('\t').append(i.toString()).append('\n');
        }
        return sb.toString();
    }
}
