package model.domain;

import java.util.ArrayList;
import java.util.List;

public class ReportPrezzi {
    List<Listino> prezzi = new ArrayList<>();
    public void addPrezzo(Listino listino){
        this.prezzi.add(listino);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Listino listino : prezzi) {
            sb.append(listino);
        }
        return sb.toString();
    }
}
