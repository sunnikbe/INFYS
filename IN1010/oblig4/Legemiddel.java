abstract class Legemiddel {
    // Felles for legemidler
    protected final String navn;
    protected int pris;
    protected final double virkestoff; // mg
    protected final int iD;
    protected static int idCounter;

    // Konstruktør:
    public Legemiddel(String n, int p, double v){
        navn = n;
        pris = p;
        virkestoff = v;
        iD = idCounter;
        ++ idCounter;
    }

    public String hentNavn(){
        return navn;
    }

    // Metode som henter prisen:
    public int hentPris(){
        return pris;
    }

    // Metode som endrer prisen:
    public void settNyPris(int nyPris){
        pris = nyPris;
    }

    @Override 
    public String toString(){
        return "\n\nLEGEMIDDEL\n----------------------"
        +"\n ID: "+iD
        +"\n Navn: "+navn
        +"\n Pris: "+pris+" kr"
        +"\n Virkestoff: "+virkestoff+" mg";
    }
}