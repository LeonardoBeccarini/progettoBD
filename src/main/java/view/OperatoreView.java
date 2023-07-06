package view;

import model.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

import java.util.Objects;
import java.util.Scanner;

public class OperatoreView {
    public static int showMenu(){
        System.out.println("*********************************");
        System.out.println("*    VERDESRL DASHBOARD    *");
        System.out.println("*********************************\n");
        System.out.println("*** What should I do for you? ***\n");
        System.out.println("1) Registra ordini");
        System.out.println("2) Aggiungi Specie ad un ordine");
        System.out.println("3) Finalizza ordine");
        System.out.println("4) Report Ordine");
        System.out.println("5) Aggiungi il contatto di una rivendita");
        System.out.println("6) Mostra rivendita");
        System.out.println("7) Aggiungi rivendita");
        System.out.println("8) Exit");


        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Please enter your choice: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 8) {
                break;
            }
            System.out.println("Invalid option");
        }

        return choice;
    }

    public static Contatto nuovoContatto() throws IOException{
        String recapito;
        String mezzo;
        String partitaIVA;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Inserire la partitaIVA della rivendita: ");
        partitaIVA = reader.readLine();
        System.out.print("recapito : ");
        recapito = reader.readLine();
        System.out.print("specificare il mezzo del recapito (email/cellulare/telefono): ");
        mezzo = reader.readLine();

        return new Contatto(partitaIVA, recapito, mezzo);
    }
    public static Contatto nuovoContattoRivendita() throws IOException{
        String recapito;
        String mezzo;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("recapito : ");
        recapito = reader.readLine();
        System.out.print("specificare il mezzo del recapito (email/cellulare/telefono): ");
        mezzo = reader.readLine();

        return new Contatto(recapito, mezzo);
    }

    public static Rivendita mostraRivendita() throws IOException{
        String partitaIVA;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Inserire la partitaIVA della rivendita: ");
        partitaIVA = reader.readLine();
        return new Rivendita(partitaIVA);
    }
    public static Ordine nuovoOrdine() throws IOException {
        String rivendita;
        String indirizzo;
        String recapito;
        Date data;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("partita IVA della rivendita: ");
        rivendita = reader.readLine();
        System.out.print("indirizzo di consegna: ");
        indirizzo = reader.readLine();
        System.out.print("contatto della rivendita : ");
        recapito =  reader.readLine();
        data = new Date(System.currentTimeMillis());
       return new Ordine(indirizzo, data, Stato.APERTO, recapito, rivendita);
    }
    public static Composizione aggiungiPianta() throws IOException{
        String pianta;
        int quantita;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);
        System.out.print("Inserisci la pianta da inserire:");
        pianta = reader.readLine();
        System.out.print("Inserisci la quantità: ");
        quantita = scan.nextInt();
        return new Composizione(pianta, quantita);
    }
    public static Rivendita aggiungiRivendita() throws IOException{
        String partitaIVA, nome, indirizzoFatturazione, indirizzoFisico, nomeReferente, cognomeReferente;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci la partita IVA: ");
        partitaIVA = reader.readLine();
        System.out.println("Inserisci il nome della rivendita: ");
        nome = reader.readLine();
        System.out.println("Inserisci l'indirizzo di fatturazione: ");
        indirizzoFatturazione = reader.readLine();
        System.out.println("Inserisci l'indirizzo fisico:  ");
        indirizzoFisico = reader.readLine();
        System.out.println("Inserisci il nome del referente: ");
        nomeReferente = reader.readLine();
        System.out.println("Inserisci il cognome del referente: ");
        cognomeReferente = reader.readLine();

        return new Rivendita(partitaIVA, nome, indirizzoFatturazione, indirizzoFisico, nomeReferente, cognomeReferente);
    }

    public static Composizione aggiungiPiantaOrdine() throws IOException{
        String codicePianta;
        int quantita;
        int codiceOrdine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci codcie ordine: ");
        codiceOrdine = scan.nextInt();
        System.out.print("Inserisci la pianta da inserire:");
        codicePianta = reader.readLine();
        System.out.print("Inserisci la quantità: ");
        quantita = scan.nextInt();
        return new Composizione( codicePianta,codiceOrdine, quantita);
    }
    public static Ordine prendiCodice() throws IOException{
        int codiceOrdine;
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci codice ordine: ");
        codiceOrdine = scan.nextInt();
        return new Ordine(codiceOrdine);
    }
    public static Ordine finalizzaOrdine() throws IOException{
        int codice;

        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci il codice dell ordine:  ");
        codice = input.nextInt();

        return new Ordine(codice, Stato.FINALIZZATO);
    }
    public static void printCodice(Ordine ordine){
        System.out.printf("Ordine con codice %d salvato con successo\n", ordine.getCodiceOrdine());
    }
    public static void printOutcome(Boolean outcome){
        if(Boolean.TRUE.equals(outcome)) System.out.println("Azione avvenuta correttamente");
    }
    public static void printRivendita(Rivendita rivendita){
        System.out.print(rivendita);
    }

}

