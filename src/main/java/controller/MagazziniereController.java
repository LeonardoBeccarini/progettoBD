package controller;

import exceptions.DAOException;
import model.dao.*;
import model.domain.*;
import view.MagazziniereView;
import view.ManagerView;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MagazziniereController implements Controller {

    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.MAGAZZINIERE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean exitNow=false;

        while (!exitNow) {

            try{
                int choice;
                choice = MagazziniereView.showMenu();

                switch (choice) {
                    case 1 -> reportGiacenze();
                    case 2 -> registraRifornimento();
                    case 3 -> mostraOrdini();
                    case 4 -> mostraComposizione();
                    case 5 -> modificaStatoOrdine();
                    case 6 -> exitNow=true;
                    default -> throw new RuntimeException("Invalid choice");
                }
            }catch(Exception e){
                System.out.println(e.toString());
            }

        }
    }

    public void reportGiacenze() {
        ReportGiacenze report;
        Giacenza giacenza;
        try{
            giacenza = MagazziniereView.getQuantita();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try {
            report = new ReportGiacenzeDAO().execute(giacenza.getQuantita());
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        MagazziniereView.stampaReport(report);
    }

    public void modificaStatoOrdine(){
        Ordine ordine;
        Boolean outcome;
        try {
            ordine = MagazziniereView.modificaStatoOrdine();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            outcome = new modificaStatoOrdineDAO().execute(ordine.getCodiceOrdine(), ordine.getStato());
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        MagazziniereView.stampaOutcome(outcome);
    }
    public void registraRifornimento(){
        Rifornimento rifornimento;
        try {
            rifornimento = MagazziniereView.getRifornimento();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            new RegistraRifornimentoDAO().execute(rifornimento.getFornitore(), rifornimento.getCodicePianta(), rifornimento.getQuantita());
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
    }
    public void mostraOrdini(){
        ArrayList<Ordine> listaOrdini;
        try {
            listaOrdini = new mostraOrdiniDAO().execute();
        }
        catch(DAOException e){
            throw new RuntimeException("errore" + e.getMessage());
        }
        MagazziniereView.stampaLista(listaOrdini);

    }

    public void mostraComposizione(){
        ArrayList<Composizione> listaSpecie;
        Ordine ordine;
        try{
            ordine = MagazziniereView.prendiCodiceOrdine();
        }catch(IOException e){
            throw new RuntimeException("errore" + e.getMessage());
        }
        try{
            listaSpecie = new mostraComposizioneDAO().execute(ordine.getCodiceOrdine());

        }catch(DAOException e){
            throw new RuntimeException("errore" + e.getMessage());
        }
        MagazziniereView.stampaComposizione(listaSpecie);
    }
}
