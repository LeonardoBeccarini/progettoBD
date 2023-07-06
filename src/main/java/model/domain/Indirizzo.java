package model.domain;

public class Indirizzo {
    private String via;
    private int nCivico;
    private String città;
    private String fornitore;

    public Indirizzo(String via, int nCivico, String città, String fornitore) {
        this.via = via;
        this.nCivico = nCivico;
        this.città = città;
        this.fornitore = fornitore;
    }

    public String getVia() {
        return via;
    }

    public int getnCivico() {
        return nCivico;
    }

    public String getCittà() {
        return città;
    }

    public String getFornitore() {
        return fornitore;
    }
}
