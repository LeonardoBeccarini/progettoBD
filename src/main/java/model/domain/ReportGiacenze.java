package model.domain;

import java.util.ArrayList;
import java.util.List;

public class ReportGiacenze {
    List<Giacenza> giacenze = new ArrayList<>();
    public void addGiacenza(Giacenza giacenza){
        this.giacenze.add(giacenza);
    }
    public void addFornitoreToReport(Fornitore fornitore, int index) {
        giacenze.get(index).addFornitore(fornitore);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Giacenza giacenza : giacenze) {
            sb.append(giacenza);
        }
        return sb.toString();
    }
}


