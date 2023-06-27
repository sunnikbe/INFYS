import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;

class Monitor1 {
    // Monitor vha komposisjon
    // kun en tråd kan sette inn hashmap av gangen
    
    private Lock laas = new ReentrantLock();
    private SubsekvensRegister subsekvenser = new SubsekvensRegister();

    public void SettInnHashMap(HashMap<String, Subsekvens> h){
        laas.lock();
        try {
            // Sett inn hashmap
            subsekvenser.SettInn(h);
        } finally {
            laas.unlock();
        }
    }

    public HashMap<String, Subsekvens> HentUtHashMap(int i){
        laas.lock();
        try {
            // Hent ut i'te HashMap
            return subsekvenser.HentUt(i);
        } finally {
            laas.unlock();
        }
    }

    public HashMap<String, Subsekvens> HentHashMap(int i){
        laas.lock();
        try {
            // Hent ut i'te HashMap
            return subsekvenser.Hent(i);
        } finally {
            laas.unlock();
        }
    }

    public int HentAntall(){
        laas.lock();
        try {
            return subsekvenser.HentAntall();
        } finally {
            laas.unlock();
        }
    }
}