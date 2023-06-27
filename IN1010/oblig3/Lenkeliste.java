abstract class Lenkeliste <E> implements Liste <E> {
    
    // Indre Node klasse
    protected class Node {
        /* Variabelen neste peker på neste Node
        for hver opprettede Node */
        Node neste = null; 
        Node forrige = null;
        E element;
        // Konstruktør
        Node(E x){
            element = x;
        }
    }
    // Enkelenket med start og slutt
    protected Node start = null;
    protected Node slutt = null;

    // Returnerer antall elementer i listen
    @Override
    public int stoerrelse(){
        int antElementer = 0;
        Node peker = start;
        while (peker != null){
            ++antElementer;
            peker = peker.neste;
        }
        return antElementer;
    }

    // Legger til element på slutten av listen
    @Override
    public void leggTil(E x){
        Node ny = new Node(x);
        // Hvis listen er tom
        if (start == null){
            start = ny;
        } else if (slutt == null && start != null) {
            // Hvis slutt noden er tom, men ikke start
            slutt = ny;
            start.neste = slutt;
        } else {
        slutt.neste = ny;
        slutt = ny;
        }
    }

    // Henter første element i listen
    @Override
    public E hent(){
        E ut = null;
        if (start != null){
            ut = start.element;
        }
        return ut;
    }

    // Fjerner første element i listen og returnerer det
    @Override
    public E fjern(){
        if (stoerrelse() == 0){
            throw new UgyldigListeindeks(0);
        }
        E bort = null;
        if (start != null){
            bort = start.element;
            // Setter neste Node i listen som start
            start = start.neste;
        }
        // Resetter Nodene dersom listen blir tom
        if (stoerrelse() == 0){
            start = null;
            slutt = null;
        }
        return bort;
    }

    @Override
    public String toString(){
        String svarstreng = "Listen inneholder elementene:";
        Node peker = start;
        while (peker != null){
            svarstreng = svarstreng +" "+ peker.element;
            peker = peker.neste;
        }
        return svarstreng;
    }
}

