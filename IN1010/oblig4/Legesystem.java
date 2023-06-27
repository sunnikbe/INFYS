import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Legesystem {
    static IndeksertListe<Pasient> pasientliste = new IndeksertListe<Pasient>();
    static IndeksertListe<Legemiddel> legemiddelliste = new IndeksertListe<Legemiddel>();
    static Prioritetskoe<Lege> legeliste = new Prioritetskoe<Lege>();
    static IndeksertListe<Resept> reseptliste = new IndeksertListe<Resept>();

    public static void lesFil(String filnavn){
        try{
            File myObj = new File(filnavn);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            String line = myReader.nextLine();
            while (myReader.hasNextLine()){
                if (line.startsWith("# Pasienter")){
                    // System.out.println(line);
                    while (myReader.hasNextLine()){
                        line = myReader.nextLine();
                        if (line.startsWith("#")){
                            break;
                        }
                        String[] info = line.split(",");
                        Pasient pasient = new Pasient(info[0], info[1]);
                        pasientliste.leggTil(i, pasient);
                        ++i;
                    }

                } else if (line.startsWith("# Legemidler")){
                    i = 0;
                    while (myReader.hasNextLine()){
                        line = myReader.nextLine();
                        if (line.startsWith("#")){
                            break;
                        }
                        Legemiddel legemiddel;
                        String[] info = line.split(",");
                        String navn = info[0];
                        String type = info[1];
                        int pris = Integer.parseInt(info[2]);
                        double virkestoff = Double.parseDouble(info[3]);
                        if (type.compareTo("narkotisk") == 0 || type.compareTo("vanedannende") == 0){
                            int styrke = Integer.parseInt(info[4]);
                            if (type.compareTo("narkotisk") == 0){
                                legemiddel = new Narkotisk(navn, pris, virkestoff, styrke);

                            } else {
                                legemiddel = new Vanedannende(navn, pris, virkestoff, styrke);

                            }
                        } else {
                            legemiddel = new Vanlig(navn, pris, virkestoff);
                        }
                        legemiddelliste.leggTil(i, legemiddel);
                        ++i;
                    }

                } else if (line.startsWith("# Leger")){
                while (myReader.hasNextLine()){
                    line = myReader.nextLine();
                    if (line.startsWith("#")){
                        break;
                    }
                    Lege lege;
                    String[] info = line.split(",");
                    if (info[1].compareTo("0") == 0){
                        lege = new Lege(info[0]);
                    } else {
                        lege = new Spesialist(info[0], info[1]);
                    }
                    legeliste.leggTil(lege);
                    }
                } else if (line.startsWith("# Resepter")){
                    i = 0;
                    while (myReader.hasNextLine()){
                        line = myReader.nextLine();
                        if (line.startsWith("#")){
                            break;
                        }
                        Resept resept = null;
                        Lege lege;
                        String[] info = line.split(",");
                        int lmnr = Integer.parseInt(info[0]);
                        int pos = Integer.parseInt(info[2]);
                        // Finner lege objekt med navn lik string som leses inn
                        for (int j = 0; j < legeliste.stoerrelse(); j++){
                            if (legeliste.hent(j).hentNavn().compareTo(info[1]) == 0){
                                lege = legeliste.hent(j);
                                // Sjekker type og oppretter riktig type resept
                                if (info[3].compareTo("militaer") == 0){
                                    resept = new MilResept(legemiddelliste.hent(lmnr), lege, pasientliste.hent(pos));
                                } else {
                                    int reit = Integer.parseInt(info[4]);
                                        if (info[3].compareTo("hvit") == 0) {
                                            resept = new Hvit(legemiddelliste.hent(lmnr), lege, pasientliste.hent(pos), reit);
                                        } else if (info[3].compareTo("p") == 0) {
                                            resept = new PResept(legemiddelliste.hent(lmnr), lege, pasientliste.hent(pos), reit);
                                        } else if (info[3].compareTo("blaa") == 0) {
                                            System.out.println(lege.utskrevneResepter.toString());
                                            resept = new Blaa(legemiddelliste.hent(lmnr), lege, pasientliste.hent(pos), reit);
                                        }
                                }
                                reseptliste.leggTil(i, resept);
                            }
                        }
                        i++;
                        }
                } else {
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e){
            System.out.println("Finner ikke filen.");
            e.printStackTrace();
        }
    }

    public static int hentIntInput(){
        Scanner myint = new Scanner(System.in);
        return myint.nextInt();
    }

    public static String hentStringInput(){
        Scanner myString = new Scanner(System.in);
        return myString.nextLine();
    }

    public static void hovedmeny(){
        boolean kjorer = true;
        while (kjorer){
            System.out.println("\n\nHOVEDMENY: \n---------------------------------------------------\n"
            +"(Skriv inn tall for ønsket valg)"
            +"\n\n0: Skriv ut oversikt over pasienter, leger, legemidler og resepter"
            +"\n1: Legg til (pasient, lege, legemiddel, resept)"
            +"\n2: Bruk en resept"
            +"\n3: Skriv ut statistikk"
            +"\n4: Avslutt"
            +"\n\n---------------------------------------------------");
            int t = hentIntInput();
            if (t == 0){
                // Oversikt over all data
                System.out.println("\n\nOVERSIKT: \n---------------------------------------------------\n"
                +"\n\nPasienter:\n---------------------------------------------------\n"
                +pasientliste.toString()
                +"\n\nLeger:\n---------------------------------------------------\n"
                +legeliste.toString()
                +"\n\n\nLegemidler:\n---------------------------------------------------\n"
                +legemiddelliste.toString()
                +"\n\n\nResepter:\n---------------------------------------------------\n"
                +reseptliste.toString()
                +"\n\n---------------------------------------------------\n");
            } else if (t == 1){
                // Legg til element
                System.out.println("\n\nLegg til element: \n---------------------------------------------------\n"
                + "\nHva vil du legge til?"
                +"\n0: Pasient"
                +"\n1: Lege"
                +"\n2: Legemiddel"
                +"\n3: Resept");
                int j = hentIntInput();
                if (j == 0){
                    System.out.println("Opprett en pasient: "
                    +"Skriv inn Navn og fødselsnummer [navn, fnr]: ");
                        String[] data = hentStringInput().split(", ");
                        Pasient pasient = new Pasient(data[0], data[1]);
                        int ind = pasientliste.stoerrelse();
                        pasientliste.leggTil((ind), pasient);
                        System.out.println("Pasient lagt til!\n"
                        +pasientliste.hent(ind).toString());
                } else if (j == 1){
                    System.out.println("Opprett en lege: "
                    +"Er legen spesialist? [j/n]");
                        String data = hentStringInput();
                        if (data.compareTo("j") == 0 || data.compareTo("ja") == 0){
                            // legen er spesialist:
                            System.out.println("Skriv inn legens navn og kontroll ID [navn, ID]: ");
                            String[] spes = hentStringInput().split(", ");
                            Lege lege = new Spesialist(spes[0], spes[1]);
                            legeliste.leggTil(lege);                              
                            System.out.println("\nLege lagt til!\n"                                   
                            +lege.toString());
                        } else if (data.compareTo("n") == 0 || data.compareTo("nei") == 0){
                            // Legen er ikke spesialist
                            System.out.println("Skriv inn legens navn [navn]:");
                            String n = hentStringInput();
                            Lege lege = new Lege(n);
                            legeliste.leggTil(lege);
                            System.out.println("\nLege lagt til!\n"
                            +lege.toString());
                        }
                } else if (j == 2){
                    System.out.println("Opprett et legemiddel: "
                    +"\nHvilken type er legemiddelet du vil legge til?"
                    +"\n0: Vanlig"
                    +"\n1: Narkotisk"
                    +"\n2: Vanedannende");
                    int k = hentIntInput();
                    int index = legemiddelliste.stoerrelse();
                    if (k == 0){
                        // Vanlig legemiddel
                        System.out.println("Vanlig legemiddel: \nSkriv inn legemiddelets navn, pris (heltall) og virkestoff i mg [navn, pris, virkestoff]: ");
                        String[] data = hentStringInput().split(", ");
                        String navn = data[0];
                        int pris = Integer.parseInt(data[1]);
                        double mg = Double.parseDouble(data[2]);
                        Legemiddel legemiddel = new Vanlig(navn, pris, mg);
                        System.out.println("\nLegemiddel lagt til:"+legemiddel.toString());
                        legemiddelliste.leggTil(index, legemiddel);
                    } else if (k == 1){
                        // Narkotisk legemiddel
                        System.out.println("Narkotisk legemiddel: "
                        +"Skriv inn legemiddelets navn, pris (heltall), virkestoff i mg og styrke (heltall) [navn, pris, virkestoff, styrke]: ");
                        String[] data = hentStringInput().split(", ");
                        String navn = data[0];
                        int pris = Integer.parseInt(data[1]);
                        double mg = Double.parseDouble(data[2]);
                        int styrke = Integer.parseInt(data[3]);
                        Legemiddel legemiddel = new Narkotisk(navn, pris, mg, styrke);
                        System.out.println("\nLegemiddel lagt til:"+legemiddel.toString());
                        legemiddelliste.leggTil(index, legemiddel);
                    } else if (k == 2){
                        // Vanedannende legemiddel
                        System.out.println("Narkotisk legemiddel: "
                        +"Skriv inn legemiddelets navn, pris (heltall), virkestoff i mg og styrke (heltall) [navn, pris, virkestoff, styrke]: ");
                        String[] data = hentStringInput().split(", ");
                        String navn = data[0];
                        int pris = Integer.parseInt(data[1]);
                        double mg = Double.parseDouble(data[2]);
                        int styrke = Integer.parseInt(data[3]);
                        Legemiddel legemiddel = new Vanedannende(navn, pris, mg, styrke);
                        System.out.println("\nLegemiddel lagt til:"+legemiddel.toString());
                        legemiddelliste.leggTil(index, legemiddel);
                    }
                } else if (j == 3){
                    System.out.println("\nOpprett en resept: "
                    +"\nHvilken lege skal skrive ut resepten?\n");
                    for (int i = 0; i < legeliste.stoerrelse(); i++){
                        // Skriv ut spesialist dersom legen er spesialist
                        String spes = "";
                        if (legeliste.hent(i) instanceof Spesialist){
                            spes = " (Spesialist)";
                        }
                        System.out.println(i+": "+legeliste.hent(i).hentNavn()+spes);
                    }
                    int l = hentIntInput();
                    System.out.println("\nHvilken pasient skal resepten skrives ut til?\n");
                    for (int i = 0; i < pasientliste.stoerrelse(); i++){
                        System.out.println(i+": "+pasientliste.hent(i).hentNavn());
                    }
                    int p = hentIntInput();
                    System.out.println("\nHvilket legemiddel skal skrives ut?\n");
                    for (int i = 0; i < legemiddelliste.stoerrelse(); i++){
                        System.out.println(i+": "+legemiddelliste.hent(i).hentNavn());
                    }
                    int lm = hentIntInput();
                    System.out.println("Skriv inn antall reiterasjoner resepten skal ha [reit]: ");
                    int reit = hentIntInput();
                    Lege lege = legeliste.hent(l);
                    Pasient pasient = pasientliste.hent(p);
                    Legemiddel legemiddel = legemiddelliste.hent(lm);
                    int k = reseptliste.stoerrelse();
                    try {
                        Resept resept = lege.skrivResept(legemiddel, pasient, reit);
                        reseptliste.leggTil(k, resept);
                        System.out.println("\nResept lagt til! \n"+resept.toString());
                    } catch (UlovligUtskrift e){
                        System.out.println("Noe gikk galt, resepten ble ikke lagt til.");
                    }
                }
            } else if (t == 2){
                // Bruk en resept
                System.out.println("\nHvilken pasient vil du bruke en resept for?");
                for (int i = 0; i < pasientliste.stoerrelse(); i++){
                    System.out.println(i+": "+pasientliste.hent(i).hentNavn());
                }
                int j = hentIntInput();
                Pasient pasient = pasientliste.hent(j);
                if (pasient.resepter.stoerrelse() == 0){
                    System.out.println("\nPasienten har ingen resepter.");
                } else {
                    System.out.println("\nHvilken resept ønsker du å bruke?");
                    for (int i = 0; i < pasient.resepter.stoerrelse(); i++){
                        System.out.println(i+": "+pasient.resepter.hent(i).hentLegemiddel().hentNavn()
                        +" ("+pasient.resepter.hent(i).hentReit()+" reit)");
                    }
                    try {
                        int k = hentIntInput();
                        Resept resept = pasient.resepter.hent(k);
                        if (resept.bruk() == true){
                            String forny = "";
                            if (resept.hentReit() == 0){
                                forny = " Husk å fornye resepten.";
                            }
                            System.out.println("Resept på "+resept.hentLegemiddel().hentNavn()+" ble brukt. "
                            + "Det er nå "+resept.hentReit()+" reiterasjoner igjen."+forny);
                        } else {
                            System.out.println("Resepten på "+resept.hentLegemiddel().hentNavn()+" er tom, og kan ikke brukes.");
                        }
                    } catch (UgyldigListeindeks e){
                        System.out.println("Det finnes ingen resept med denne indeksen. Vennligst prøv på nytt.");
                    }
                }
            } else if (t == 3){
                // Skriv ut statistikk
                System.out.println("\nVis statistikk: \nVennligst velg ønsket statistikk:"
                +"\n0: Totalt antall utskrevne resepter på vanedannende og narkotiske legemidler"
                +"\n1: Statistikk om mulig misbruk av narkotiske legemidler");
                int j = hentIntInput();
                if (j == 0){
                    // Totalt utskrevne resepter på vanedannende legemidler
                    int antall = 0;
                    for (int k = 0; k < reseptliste.stoerrelse(); k++){
                        if (reseptliste.hent(k).hentLegemiddel() instanceof Vanedannende){
                            ++antall;
                        }
                    }
                    System.out.println("\nTotalt antall utskrevne resepter på:"
                    +"\n------------------------------------\n"
                    +" Vanedannende legemidler:    "+antall);
                    // Totalt antall utskrevne resepter på narkotiske legemidler
                    int ant = 0;
                    for (int k = 0; k < reseptliste.stoerrelse(); k++){
                        if (reseptliste.hent(k).hentLegemiddel() instanceof Narkotisk){
                            ++ant;
                        }
                    }
                    System.out.println(" Narkotiske legemidler:      "+ant+"\n");
                } else if (j == 1){
                    // Statistikk om mulig misbruk av narkotika
                    System.out.println("\nListe over leger som har skrevet ut minst en resept på narkotiske legemidler:"
                    +"\n-----------------------------------------------------------------------------");
                    Prioritetskoe<Lege> narkleger = new Prioritetskoe<Lege>(); 
                    for (int k = 0; k < legeliste.stoerrelse(); k++){
                        for (int l = 0; l < legeliste.hent(k).hentResepter().stoerrelse(); l++){
                            if (legeliste.hent(k).hentResepter().hent(l).hentLegemiddel() instanceof Narkotisk){
                                narkleger.leggTil(legeliste.hent(k));
                                // Legen vil ikke legges til dobbelt ved flere resepter på narkotiske legemidler
                                break;
                            }
                        }
                    }
                    for (int k = 0; k < narkleger.stoerrelse(); k++){
                        int narkresepter = 0;
                        for (int l = 0; l < narkleger.hent(k).hentResepter().stoerrelse(); l++){
                            if (narkleger.hent(k).hentResepter().hent(l).hentLegemiddel() instanceof Narkotisk){
                                ++narkresepter;
                            }
                        }
                        System.out.println("\n Lege: "+narkleger.hent(k).hentNavn()
                        +"\n Antall resepter skrevet: "+narkresepter+"\n");
                    }
                    System.out.println("\nListe over pasienter som har minst en resept på narkotiske legemidler:"
                    +"\n----------------------------------------------------------------------");
                    IndeksertListe<Pasient> narkpas = new IndeksertListe<Pasient>(); 
                    for (int k = 0; k < pasientliste.stoerrelse(); k++){
                        for (int l = 0; l < pasientliste.hent(k).hentResepter().stoerrelse(); l++){
                            if (pasientliste.hent(k).hentResepter().hent(l).hentLegemiddel() instanceof Narkotisk){
                                narkpas.leggTil(pasientliste.hent(k));
                                // Pasienten vil ikke legges til dobbelt ved flere resepter på narkotiske legemidler
                                break;
                            }
                        }
                    }
                    for (int k = 0; k < narkpas.stoerrelse(); k++){
                        int narkresepter = 0;
                        for (int l = 0; l < narkpas.hent(k).hentResepter().stoerrelse(); l++){
                            if (narkpas.hent(k).hentResepter().hent(l).hentLegemiddel() instanceof Narkotisk){
                                ++narkresepter;
                            }
                        }
                        System.out.println("\n Pasient: "+narkpas.hent(k).hentNavn()
                        +"\n Antall resepter: "+narkresepter+"\n");
                    }
                }
            } else if (t == 4){
                kjorer = false;
            } 
        }
    }

    public static void main(String[] args){
        lesFil("legedata.txt");
        hovedmeny();
    }
}