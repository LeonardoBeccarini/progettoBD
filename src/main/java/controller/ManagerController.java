package controller;

import exceptions.DAOException;
import model.dao.*;
import model.domain.*;
import view.ManagerView;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static view.ManagerView.printSpecie;

public class ManagerController implements Controller {
    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.MANAGER);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            int choice;
            choice = ManagerView.showMenu();

            switch (choice) {
                case 1 -> registraSpecie();
                case 2 -> modificaPrezzo();
                case 3 -> reportPrezzi();
                case 4 -> aggiungiColorazione();
                case 5 -> aggiungiIndirizzo();
                case 6 -> System.exit(0);
                default -> throw new RuntimeException("Invalid choice");
            }
        }
    }

    public void registraSpecie() {
        SpeciePianta specie;
        Fornitore fornitore;
        try {
            specie = ManagerView.nuovaSpecie();
            fornitore = ManagerView.prendiFornitore();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            specie = new RegistraSpecieDAO().execute(specie.getCodicePianta(), specie.getNomeLatino(), specie.getNomeComune(), specie.getGiacenza(),
                    specie.isEsotica(), specie.isEsterno(), specie.getColore(), specie.getPrezzo(), fornitore.getCodice());
        }
        catch(DAOException e){
            throw new RuntimeException(e);
        }
        if(specie != null){
            printSpecie(specie);
        }
    }

    public void modificaPrezzo(){
        Listino listino;
        Boolean outcome;
        try{
            listino = ManagerView.cambiaPrezzo();
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            outcome = new ModificaPrezzoDAO().execute(listino.getPianta(), listino.getPrezzo());
            ManagerView.printOutcome(outcome);
        }
        catch(DAOException e){
            throw new RuntimeException(e);
        }
    }
    public void reportPrezzi() {
        Listino listino;
        ReportPrezzi reportPrezzi;
        try{
            listino = ManagerView.prendiDatiReport();

        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            reportPrezzi = new ReportPrezziDAO().execute(listino.getPianta(), listino.getDataFine());
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        System.out.print(reportPrezzi);
    }
    public void aggiungiColorazione(){
        Colorazione colorazione;
        Boolean outcome;
        try{
            colorazione = ManagerView.nuovaColorazione();

        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            outcome = new aggiungiColorazioneDAO().execute(colorazione.getPianta(), colorazione.getColore());
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        ManagerView.printOutcome(outcome);
    }

    public void aggiungiIndirizzo(){
        Indirizzo indirizzo;
        Boolean outcome;
        try{
            indirizzo = ManagerView.nuovoIndirizzo();

        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            outcome = new aggiungiIndirizzoDAO().execute(indirizzo.getVia(), indirizzo.getnCivico(), indirizzo.getCittà(), indirizzo.getFornitore());
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        ManagerView.printOutcome(outcome);

    }
}

