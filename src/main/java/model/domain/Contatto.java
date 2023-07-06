package model.domain;

public class Contatto {
    private String recapito;
    private String mezzo;
    private String rivendita;

    public Contatto(String recapito, String mezzo) {
        this.recapito = recapito;
        this.mezzo = mezzo;
    }

    public Contatto(String partitaIVA, String recapito, String mezzo) {
        this.recapito = recapito;
        this.mezzo = mezzo;
        this.rivendita = partitaIVA;
    }

    public String getRecapito(){
        return recapito;
    }

    public String getMezzo() {
        return mezzo;
    }

    public String getRivendita() {
        return rivendita;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("recapito:").append(recapito).append(" ").append("mezzo:").append(' ').append(mezzo).append(" ").append("\n");
        return sb.toString();
    }
}

