package model.domain;

import java.util.ArrayList;
import java.util.List;

public class Rivendita {
    private String partitaIVA;
    private String nome;
    private String indirizzoFatturazione;
    private String indirizzoFisico;
    private String nomeReferente;
    private String cognomeReferente;
    List<Contatto> contatti = new ArrayList<>();

    public Rivendita(String partitaIVA, String nome, String indirizzoFatturazione, String indirizzoFisico, String nomeReferente, String cognomeReferente) {
        this.partitaIVA = partitaIVA;
        this.nome = nome;
        this.indirizzoFatturazione = indirizzoFatturazione;
        this.indirizzoFisico = indirizzoFisico;
        this.nomeReferente = nomeReferente;
        this.cognomeReferente = cognomeReferente;
    }
    public Rivendita(String partitaIVA) {
        this.partitaIVA = partitaIVA;
    }

    public String getPartitaIVA() {
        return partitaIVA;
    }

    public void addContatto(Contatto contatto){
        this.contatti.add(contatto);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(partitaIVA).append(" ").append(indirizzoFatturazione).append(" ").append(indirizzoFisico).append(" ").append(nomeReferente).append(" ").append(cognomeReferente).append(" ): \n");
        for(Contatto i: contatti) {
            sb.append('\t').append(i.toString()).append('\n');
        }
        return sb.toString();
    }

    public String getIndirizzoFatturazione() {
        return indirizzoFatturazione;
    }

    public String getIndirizzoFisico() {
        return indirizzoFisico;
    }

    public String getNomeReferente() {
        return nomeReferente;
    }

    public String getCognomeReferente() {
        return cognomeReferente;
    }
}
