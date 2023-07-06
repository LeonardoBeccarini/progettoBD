package model.domain;

import java.util.ArrayList;
import java.util.List;

public class ReportOrdine {
    Ordine ordine;
    List<Composizione> specieOrdine= new ArrayList<>();
    int quantitaTotale;
    float prezzoTotale;

    public void addSpecieToOrderReport(Composizione composizione){
        this.specieOrdine.add(composizione);
    }
    public void addOrdine(Ordine ordine){
        this.ordine = ordine;
    }
    public void addQuantitaPrezzoTotali(int quantita, float prezzo){
        this.quantitaTotale = quantita;
        this.prezzoTotale = prezzo;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ordine).append(": \n");

        for(Composizione composizione : specieOrdine) {
            sb.append(composizione);
        }
        sb.append("prezzo totale: ").append(prezzoTotale).append("\n");
        return sb.toString();
    }
}
