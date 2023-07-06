package controller;


import exceptions.DAOException;
import model.dao.*;
import model.domain.*;
import view.OperatoreView;

import java.io.IOException;
import java.sql.SQLException;


public class OperatoreController implements Controller{
        @Override
        public void start() {
                try {
                        ConnectionFactory.changeRole(Role.OPERATORE);
                } catch(SQLException e) {
                        throw new RuntimeException(e);
                }

                boolean exitNow=false;
                while(!exitNow) {
                        int choice;
                        choice = OperatoreView.showMenu();

                        switch(choice) {
                                case 1 -> registraOrdine();
                                case 2 -> aggiungiSpecieOrdine();
                                case 3 -> finalizzaOrdine();
                                case 4 -> reportOrdine();
                                case 5 -> aggiungiContatto();
                                case 6 ->mostraRivendita();
                                case 7 -> registraRivendita();
                                case 8 -> exitNow=true;
                                default -> throw new RuntimeException("Invalid choice");
                        }
                }
        }

        public void aggiungiContatto(){
                Contatto contatto;
                try {
                        contatto = OperatoreView.nuovoContatto();
                }
                catch(IOException e){
                        throw new RuntimeException(e);
                }
                try {
                         new AggiungiContattoDAO().execute(contatto.getRivendita(), contatto.getRecapito(), contatto.getMezzo());
                }
                catch(DAOException e) {
                        throw new RuntimeException(e);
                }

        }
        public void mostraRivendita() {
                Rivendita rivendita_temp;
                Rivendita rivendita;
                try {
                        rivendita_temp = OperatoreView.mostraRivendita();
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
                try {
                        rivendita = new mostraRivenditaDAO().execute(rivendita_temp.getPartitaIVA());

                } catch (DAOException e) {
                        throw new RuntimeException(e);
                }
                OperatoreView.printRivendita(rivendita);

        }
        public void registraOrdine(){
                Ordine temp;
                Ordine ordine;
                Composizione composizione;
                try {
                        temp = OperatoreView.nuovoOrdine();
                        composizione = OperatoreView.aggiungiPianta();
                }
                catch(IOException e){
                        throw new RuntimeException(e);
                }
                try {
                        ordine = new ApriOrdineDAO().execute(temp.getRivendita(), temp.getIndirizzo(), temp.getRecapito(), composizione.getCodiceSpecie(), composizione.getQuantita());
                }
                catch(DAOException e) {
                        throw new RuntimeException(e);
                }
                if(ordine != null) OperatoreView.printCodice(ordine);
        }
        public void aggiungiSpecieOrdine(){
                Composizione composizione;
                try{
                    composizione = OperatoreView.aggiungiPiantaOrdine();
                }catch(IOException e){
                        throw new RuntimeException(e);
                }
                try{
                        new AggiungiSpecieDAO().execute(composizione.getCodiceOrdine(), composizione.getCodiceSpecie(), composizione.getQuantita());
                }catch(DAOException e){
                        throw new RuntimeException(e);
                }
        }
        public void finalizzaOrdine(){
             Ordine ordine;
             Boolean outcome;
             try{
                     ordine = OperatoreView.finalizzaOrdine();
             }catch(IOException e){
                     throw new RuntimeException(e);
             }
             try{
                    outcome =  new modificaStatoOrdineDAO().execute(ordine.getCodiceOrdine(), ordine.getStato());
             }
             catch(DAOException e){
                     throw new RuntimeException(e);
             }
             OperatoreView.printOutcome(outcome);
        }
        public void reportOrdine(){
                Ordine ordine;
                ReportOrdine report;
                try{
                        ordine = OperatoreView.prendiCodice();
                }catch(IOException e){
                        throw new RuntimeException(e);
                }
                try{
                        report = new ReportOrdineDAO().execute(ordine.getCodiceOrdine());
                }catch(DAOException e){
                        throw new RuntimeException(e);
                }
                System.out.print(report);
        }

        public void registraRivendita(){
                Rivendita rivendita;
                Contatto contatto;
                Boolean outcome;
                try{
                        rivendita = OperatoreView.aggiungiRivendita();
                        contatto = OperatoreView.nuovoContattoRivendita();
                }catch(IOException e){
                        throw new RuntimeException(e);
                }
                try{
                        outcome = new registraRivenditaDAO().execute(rivendita.getPartitaIVA(), rivendita.getIndirizzoFatturazione(),
                                rivendita.getIndirizzoFisico(), rivendita.getNomeReferente(), rivendita.getCognomeReferente(),
                                contatto.getRecapito(), contatto.getMezzo());
                }catch(DAOException e){
                        throw new RuntimeException(e);
                }
                OperatoreView.printOutcome(outcome);
        }
}



