import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Oblig5Del1 {    
    // Testprogram

    public static void TestSubsekvensRegister(String filgruppe){
        SubsekvensRegister subsekvenser = new SubsekvensRegister();

        // Opretter pathen til filen metadata gitt filnavn
        
        // NB! Uncomment under hvis du kjører fra Oblig5Del1 mappen!!
        String path = "../Oblig5Del1/"+filgruppe+"/metadata.csv";
        // String path = "../Oblig5/"+filgruppe+"/metadata.csv";

        try {
            File metadata = new File(path);
            Scanner myReader = new Scanner(metadata);
            // For hver linje i metadata, les filen vha metoden LesFil()
            while (myReader.hasNextLine()){

                // Hvis du kjører fra Obli5Del1 mappen, uncomment:
                String filnavn = "../Oblig5Del1/"+filgruppe+"/"+myReader.nextLine();
                // String filnavn = "../Oblig5/"+filgruppe+"/"+myReader.nextLine();

                HashMap<String, Subsekvens> hashmap = SubsekvensRegister.LesFil(filnavn);
                // Alle hashmapene legges inn i subsekvensregisteret (steg 1)
                subsekvenser.SettInn(hashmap);
            }
            myReader.close();
        } catch (FileNotFoundException e){
            System.out.println("Filgruppen ikke funnet, sjekk at du har skrevet inn riktig.\n"
            +"(Hvis du kjører fra Oblig5Del1 mappen, les kommentarer i koden.)");
        }

        // hent ut og flett alle hashmapene sammen (steg 2)
        while (subsekvenser.HentAntall() > 2){
            for (int i = 0; i < subsekvenser.HentAntall(); i++){
                HashMap<String, Subsekvens> hash1 = subsekvenser.HentUt(i);
                HashMap<String, Subsekvens> hash2 = new HashMap<String, Subsekvens>();
                if (i < subsekvenser.HentAntall()) {
                    // Samme indeks siden i allerede har blitt fjernet fra arraylisten subsekvenser
                    hash2 = subsekvenser.HentUt(i); 
                }
                // Flett to og to
                HashMap<String, Subsekvens> flettet = SubsekvensRegister.SettSammen(hash1, hash2);
                subsekvenser.SettInn(flettet);
            }
        }

        // Siste fletting
        HashMap<String, Subsekvens> hash1 = subsekvenser.HentUt(0);
        HashMap<String, Subsekvens> hash2 = subsekvenser.HentUt(0);
        HashMap<String, Subsekvens> flettet = SubsekvensRegister.SettSammen(hash1, hash2);
        subsekvenser.SettInn(flettet);

        // Skriv ut subsekvensen med flest forekomster
        if (subsekvenser.HentAntall() == 1){
            HashMap<String, Subsekvens> hashmap = subsekvenser.Hent(0);
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
        TestSubsekvensRegister("TestDataLike");
        TestSubsekvensRegister("TestDataLitenLike");

    }
}