package view;

import model.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class MagazziniereView {
    public static int showMenu() {
        System.out.println("*********************************");
        System.out.println("*    VERDESRL DASHBOARD    *");
        System.out.println("*********************************\n");
        System.out.println("*** What should I do for you? ***\n");
        System.out.println("1) Report giacenze");
        System.out.println("2) Registra rifornimento");
        System.out.println("3) Mostra ordini da spedire");
        System.out.println("4) Mostra le specie di piante in un ordine");
        System.out.println("5) Modifica lo stato di un ordine");
        System.out.println("6) Exit");


        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 6) {
                break;
            }
            System.out.println("Invalid option");
        }

        return choice;
    }
    public static Giacenza getQuantita() throws IOException{
        int giacenza;

        Scanner input = new Scanner(System.in);
        System.out.print("Quantità: ");
        giacenza = input.nextInt();
        return new Giacenza(giacenza);

    }
    public static Rifornimento getRifornimento() throws IOException{
        int quantita;
        String fornitore;
        String codice;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner input = new Scanner(System.in);
        System.out.println("inserisci codice alfanumerico fornitore: ");
        fornitore = reader.readLine();
        System.out.println("inserisci codice alfanumerico specie: ");
        codice = reader.readLine();
        System.out.print("Quantità: ");
        quantita = input.nextInt();
        return new Rifornimento(fornitore, codice, quantita);
    }

    public static void stampaLista(ArrayList<Ordine> lista){
        for(Ordine ordine: lista) {
            System.out.printf(
                    "Codice Ordine: %s%n Indirizzo di Consegna: %s%n Data: %s%n Partita IVA della rivendita: %s%n Recapito della rivendita: %s%n%n ",
                    ordine.getCodiceOrdine(), ordine.getIndirizzo(),ordine.getData(),ordine.getRivendita(), ordine.getRecapito());
        }
    }
    public static Ordine prendiCodiceOrdine() throws IOException{
        Ordine ordine;
        int codice;
        Scanner input = new Scanner(System.in);
        System.out.println("inserisci codice ordine: ");
        codice = input.nextInt();
        ordine = new Ordine(codice);
        return ordine;
    }
    public static void stampaComposizione(ArrayList<Composizione> lista){
        for(Composizione composizione: lista) {
            System.out.printf(
                    "%s%n Quantita: %s%n%n",
                    composizione.getCodiceSpecie(), composizione.getQuantita());
        }
    }
    public static Ordine modificaStatoOrdine() throws IOException{
        int codice;
        Stato stato = null;
        String risposta;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci il codice dell ordine:  ");
        codice = input.nextInt();
        System.out.println("Inserisci nuovo stato(spedito/consegnato): ");
        risposta = reader.readLine();

        if (Objects.equals(risposta, "spedito")) stato = Stato.SPEDITO;
        else if (Objects.equals(risposta, "consegnato")) stato = Stato.CONSEGNATO;
        else throw new IOException();

        return new Ordine(codice, stato);
    }
    public static void stampaReport(ReportGiacenze report){
        System.out.print(report);
    }
    public static void stampaOutcome(Boolean outcome){
        if(Boolean.TRUE.equals(outcome)) System.out.println("Azione avvenuta correttamente");
    }

}

