package model.domain;

public class SpeciePianta {
    private String codicePianta;
    private String nomeLatino;
    private String nomeComune;
    private int giacenza;
    private String colore;
    private float prezzo;

    private boolean esotica;
    private boolean esterno;
    private boolean fiorita;

    public SpeciePianta(String codicePianta, String nomeLatino, String nomeComune, int giacenza, float prezzo, boolean esotica, boolean esterno, boolean fiorita) {
        this.codicePianta = codicePianta;
        this.nomeLatino = nomeLatino;
        this.nomeComune = nomeComune;
        this.giacenza = giacenza;
        this.prezzo = prezzo;
        this.esotica = esotica;
        this.esterno = esterno;
        this.fiorita = fiorita;
    }

    public SpeciePianta(String codicePianta, String nomeLatino, String nomeComune, int giacenza, float prezzo, String colore, boolean esotica, boolean esterno) {
        this.codicePianta = codicePianta;
        this.nomeLatino = nomeLatino;
        this.nomeComune = nomeComune;
        this.giacenza = giacenza;
        this.prezzo = prezzo;
        this.colore = colore;
        this.esotica = esotica;
        this.esterno = esterno;
    }

    public String getCodicePianta() {
        return codicePianta;
    }

    public String getNomeLatino() {
        return nomeLatino;
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public int getGiacenza() {
        return giacenza;
    }

    public boolean isEsotica() {
        return esotica;
    }

    public boolean isEsterno() {
        return esterno;
    }

    public boolean isFiorita() {
        return fiorita;
    }

    public String getColore() {
        return colore;
    }

    public float getPrezzo() {
        return prezzo;
    }
}
