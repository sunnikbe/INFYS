import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class SubsekvensRegister{
    public static ArrayList<HashMap<String, Subsekvens>> hashBeholder; 

    SubsekvensRegister(){
       hashBeholder = new ArrayList<HashMap<String, Subsekvens>>(); 
    }

    public void SettInn(HashMap<String, Subsekvens> h){
        hashBeholder.add(h);
    }

    public HashMap<String, Subsekvens> Hent(int i){
        return hashBeholder.get(i);
    }

    public HashMap<String, Subsekvens> HentUt(int i){
        HashMap<String, Subsekvens> ut = hashBeholder.get(i);
        hashBeholder.remove(i);
        return ut;
    }

    public int HentAntall(){
        return hashBeholder.size();
    }

    public static HashMap<String, Subsekvens> LesFil(String filnavn){
        HashMap<String, Subsekvens> hash = new HashMap<>();
        try {
            File f = new File(filnavn);
            Scanner myReader = new Scanner(f);
            // Leser filen linje for linje
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                // Les tre tegn i linjen av gangen
                if (data.length() >= 3){
                    for (int i = 0; i < data.length() - 2; i++){
                        String string = data.substring(i, i + 3);
                        // Legg kun til subsek dersom den ikke finnes i hashmapen fra før
                        if (!hash.containsKey(string)){
                            Subsekvens subsek = new Subsekvens(string);
                            hash.put(string, subsek);
                        }
                    }
                    
                } else {
                    // Stopp om en linje er kortere enn 3 tegn
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e){
            System.out.println("Filen ble ikke funnet, prøv på nytt.");
        }
        return hash;
    }

    public static HashMap<String, Subsekvens> SettSammen(HashMap<String, Subsekvens> h1, HashMap<String, Subsekvens> h2){
        // Legg hver key fra h1 inn i h2, hvis den ikke finnes der fra før
        for (String i : h1.keySet()){
            // Legg inn i ny kun en gang
            if (!h2.containsKey(i)){
                h2.put(i, h1.get(i));
            }
            // Oppdater ant for to like subsekvenser
            else if (h2.containsKey(i)){
                int a1 = h2.get(i).HentAntall();
                int a2 = h1.get(i).HentAntall();
                h2.get(i).EndreAntall(a1 + a2);
            }
        }
        return h2;
    }

    @Override
    public String toString(){
        String streng = "\nSubsekvensregister:\n";
        for (int i = 0; i < hashBeholder.size(); i++){
            streng += "\nHashMap "+i+": \n";
            for (String j : Hent(i).keySet())
            streng += "String: " + j + "  Subsekvens: " + Hent(i).get(j) + "\n";
        }
        return streng;
    }

    public static void main(String[] args){
        // SubsekvensRegister subsekvenser = new SubsekvensRegister();
        // HashMap<String, Subsekvens> hash3 = SubsekvensRegister.LesFil("../Oblig5/TestOppgaveEks/fil1.csv");
        // HashMap<String, Subsekvens> hash4 = SubsekvensRegister.LesFil("../Oblig5/TestOppgaveEks/fil2.csv");
        // // HashMap<String, Subsekvens> hash = new HashMap<String, Subsekvens>();
        // // subsekvenser.SettInn(hash3);
        // // subsekvenser.SettInn(hash4);
        // HashMap<String, Subsekvens> hash5 = SubsekvensRegister.SettSammen(hash3, hash4);
        // subsekvenser.SettInn(hash5);
        // System.out.println(subsekvenser.toString());
        // hash.put("ABC", subsekvens);
        // hash.put("BCA", subsekvens);
        // HashMap<String, Subsekvens> hash2 = new HashMap<String, Subsekvens>();
        // hash2.put("BDA", subsekvens);
        // hashBeholder.SettInn(hash);
        // hashBeholder.SettInn(hash2);
        // System.out.println(hashBeholder.toString());
        // HashMap<String, Subsekvens> hash3 = hashBeholder.Hent(0);
        // System.out.println(hash3.toString());
    }
}