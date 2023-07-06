package view;

import model.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Date;
import java.util.Scanner;

public class ManagerView {
    public static int showMenu(){
        System.out.println("*********************************");
        System.out.println("*    VERDESRL DASHBOARD    *");
        System.out.println("*********************************\n");
        System.out.println("*** What should I do for you? ***\n");
        System.out.println("1) Registra specie");
        System.out.println("2) Modifica prezzo");
        System.out.println("3) Report prezzi");
        System.out.println("4) Aggiungi colorazione ad una specie fiorita");
        System.out.println("5) Aggiungi indirizzo ad un fornitore");
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
    public static SpeciePianta nuovaSpecie() throws IOException {
        String codicePianta;
        String nomeLatino;
        String nomeComune;
        int giacenza;
        boolean esotica = false;
        boolean esterno = false;
        String colore;
        String risposta;
        float prezzo;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("codice alfanumerico identificativo della specie: ");
        codicePianta = reader.readLine();
        System.out.print("nome latino ");
        nomeLatino = reader.readLine();
        System.out.print("nome comune: ");
        nomeComune = reader.readLine();

        System.out.println("la specie è esotica? :)");
        risposta = reader.readLine();
        if(risposta.equals("si") || risposta.equals("Si")) esotica=true;

        System.out.println("la specie è da esterno? :)");
        risposta = reader.readLine();
        if(risposta.equals("si") || risposta.equals("Si")) esterno=true;

        System.out.println("se la specie è fiorita inserire un colore per cui è disponibile");
        colore = reader.readLine();

        Scanner input = new Scanner(System.in);
        System.out.print("Giacenza in magazzino della nuova specie: ");
        giacenza = input.nextInt();
        System.out.println("prezzo della nuova specie: ");
        prezzo = input.nextFloat();
        return new SpeciePianta(codicePianta, nomeLatino, nomeComune, giacenza, prezzo, colore, esotica, esterno);

    }
    public static Fornitore prendiFornitore() throws IOException{
        String codice;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("codice alfanumerico identificativo del fornitore: ");
        codice = reader.readLine();
        return new Fornitore(codice);
    }
    public static void printSpecie(SpeciePianta specie){
        System.out.println("-------------------------------------------");
        System.out.println("Hai inserito:");
        System.out.printf("%s(%s)%n", specie.getNomeComune(), specie.getNomeLatino());
        System.out.printf("In quantità : %d, al prezzo al pezzo di %f%n", specie.getGiacenza(), specie.getPrezzo());
        System.out.println("-------------------------------------------");
    }

    public static Listino cambiaPrezzo() throws IOException{
        float prezzo;
        String pianta;
        Listino listino;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("codice alfanumerico identificativo della specie: ");
        pianta = reader.readLine();

        Scanner input = new Scanner(System.in);
        System.out.print("Inserisci il nuovo prezzo: ");
        prezzo = input.nextFloat();
        listino = new Listino(pianta, prezzo);
        return listino;
    }
    public static Listino prendiDatiReport() throws IOException{
        String pianta;
        String temp;
        Date dataFine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Codice alfanumerico pianta:");
        pianta = reader.readLine();
        System.out.print("Fino a che data fa arrivare il report (YYYY-MM-DD):");
        temp = reader.readLine();
        dataFine = Date.valueOf(temp);
        return new Listino(pianta, dataFine);
    }

    public static Indirizzo nuovoIndirizzo() throws IOException{
        String via;
        int nCivico;
        String città, fornitore;
        Scanner input = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Nome della via: ");
        via = reader.readLine();
        System.out.print("N. civico: ");
        nCivico = input.nextInt();
        System.out.print("Città: ");
        città = reader.readLine();
        System.out.print("codice alfanumerico del fornitore: ");
        fornitore = reader.readLine();
        return new Indirizzo(via, nCivico, città, fornitore);
    }

    public static Colorazione nuovaColorazione() throws IOException{
        String colore, pianta;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Codice alfanumerico della pianta: ");
        pianta = reader.readLine();
        System.out.print("Nuovo colore: ");
        colore = reader.readLine();
        return new Colorazione(pianta, colore);
    }

    public static void printOutcome(Boolean outcome){
        if(outcome){
            System.out.println("aggiunta andata a buon fine");
        }
        else System.out.println("aggiunta non riuscita");
    }
}
