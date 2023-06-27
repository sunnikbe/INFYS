import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

class LeseTrad implements Runnable{

    private String filnavn;
    private Monitor1 monitor;
    // private int index;
    private CountDownLatch barriere;
    
    // Leser en fil og legger hashmapen inn i en 
    // beholder av Monitor1
    public LeseTrad(String fn, Monitor1 mon, CountDownLatch b){
        filnavn = fn;
        monitor = mon; 
        // i = index;
        barriere = b;       
    }

    @Override
    public void run(){
        HashMap<String, Subsekvens> h = SubsekvensRegister.LesFil(filnavn);
        monitor.SettInnHashMap(h);
        barriere.countDown();
    }
}