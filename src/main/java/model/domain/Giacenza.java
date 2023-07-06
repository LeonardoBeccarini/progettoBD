package model.domain;

import java.util.ArrayList;
import java.util.List;

public class Giacenza {
    String codicePianta;
    String nome;
    int quantita;
    int index;
   List<Fornitore> fornitori = new ArrayList<>();

   public Giacenza(String codicePianta, String nome, int giacenza, int index){
       this.index = index;
       this.codicePianta = codicePianta;
       this.nome = nome;
       this.quantita = giacenza;
   }
    public Giacenza(int quantita){
       this.quantita = quantita;
   }

    public int getQuantita() {
        return this.quantita;
    }

   public void addFornitore(Fornitore fornitore) {
        this.fornitori.add(fornitore);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(codicePianta).append(" ").append(nome).append('-').append("Quantità: ").append(quantita).append("\n");
        for(Fornitore p: fornitori) {
            sb.append('\t').append(p.toString()).append('\n');
        }
        return sb.toString();
    }
}
