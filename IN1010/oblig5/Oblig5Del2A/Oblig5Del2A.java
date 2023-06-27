import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.File;
import java.io.FileNotFoundException;

class Oblig5Del2A {    
        static Monitor1 monitor;
        static CountDownLatch barriere;

    public static void TestMonitor1(String filgruppe){
        // Opretter pathen til filen metadata gitt filnavn
        
        // NB! Uncomment under hvis du kjører fra Oblig5Del2A mappen!!
        String path = "../Oblig5Del2A/"+filgruppe+"/metadata.csv";
        // String path = "../Oblig5/"+filgruppe+"/metadata.csv";

        try {
            File metadata = new File(path);
            Scanner myCounter = new Scanner(metadata);
            Scanner myReader = new Scanner(metadata);
            // For hver linje i metadata, les filen vha monitor 1
            int antFiler = 0;
            while (myCounter.hasNextLine()){
                ++antFiler;
                }
            myCounter.close();

            barriere = new CountDownLatch(antFiler);

            System.out.println("Ant Filer skal være 9 og 3" + antFiler);
            Thread[] tradene = new Thread[antFiler];
            int i = 0;
            while (myReader.hasNextLine()){
                // Hvis du kjører fra Obli5Del2A mappen, uncomment:
                String filnavn = "../Oblig5Del2A/"+filgruppe+"/"+myReader.nextLine();
                // String filnavn = "../Oblig5/"+filgruppe+"/"+myReader.nextLine();

                Runnable LeseTrad = new LeseTrad(filnavn, monitor, barriere);
                tradene[i] = new Thread(LeseTrad);
                tradene[i].start();

                ++i;    
            }

            try {
                barriere.await();
            } catch (InterruptedException e){
                System.out.println("Interruption i barriere.await()" + e);
            }

            for (int j = 0; j < antFiler; j++){
                tradene[j].join();
            }
            myReader.close();
        } catch (InterruptedException e){
            System.out.println("En tråd har blitt forstyrret");
        } catch (FileNotFoundException e){
            System.out.println("Filgruppen ikke funnet, sjekk at du har skrevet inn riktig.\n"
            +"(Hvis du kjører fra Oblig5Del2A mappen, les kommentarer i koden.)");
        }

        // Start flettingen inne i Monitor1, kun en tråd!

        while (monitor.HentAntall() > 2){
            for (int i = 0; i < monitor.HentAntall(); i++){
                HashMap<String, Subsekvens> hash1 = monitor.HentUtHashMap(i);
                HashMap<String, Subsekvens> hash2 = new HashMap<String, Subsekvens>();
                if (i < monitor.HentAntall()) {
                    // Samme indeks siden i allerede har blitt fjernet fra arraylisten subsekvenser
                    hash2 = monitor.HentUtHashMap(i); 
                }
                // Flett to og to
                HashMap<String, Subsekvens> flettet = SubsekvensRegister.SettSammen(hash1, hash2);
                monitor.SettInnHashMap(flettet);
            }
        }

        // Siste fletting
        HashMap<String, Subsekvens> hash1 = monitor.HentUtHashMap(0);
        HashMap<String, Subsekvens> hash2 = monitor.HentUtHashMap(0);
        HashMap<String, Subsekvens> flettet = SubsekvensRegister.SettSammen(hash1, hash2);
        monitor.SettInnHashMap(flettet);

        // Skriv ut subsekvensen med flest forekomster
        if (monitor.HentAntall() == 1){
            HashMap<String, Subsekvens> hashmap = monitor.HentHashMap(0);
            int max = 0;
            String subsek = "";
            for (String i : hashmap.keySet()){
                if (hashmap.get(i).HentAntall() > max){
                    subsek = hashmap.get(i).toString();
                    max = hashmap.get(i).HentAntall();
                }
            }
            System.out.println("\nSubsekvensen med flest forekomster i mappen "
            +filgruppe+": \n"
            +subsek+"\n");
        }
    }

    public static void main(String[] args){
        TestMonitor1("TestDataLike");
        TestMonitor1("TestDataLitenLike");
        
    }
}