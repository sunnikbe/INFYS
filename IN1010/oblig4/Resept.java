abstract class Resept {
    // Instansvariabler felles for resepter
    protected final int reseptId;
    protected static int resIdCounter;
    protected final Legemiddel legemiddel;
    protected final Lege utskrivendeLege;
    protected final Pasient pasient;
    protected int reit;

    // Konstruktør
    public Resept(Legemiddel l, Lege uL, Pasient p, int r){
        legemiddel = l;
        utskrivendeLege = uL;
        pasient = p;
        reit = r;
        reseptId = resIdCounter;
        ++resIdCounter;
        utskrivendeLege.leggTilResept(this);
        pasient.leggTilResept(this);
    }
    
    // Metode som henter ID
    public int hentId(){
        return reseptId;
    }

    // Metode som henter legemiddel
    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    // Metode som henter utskrivende lege
    public Lege hentLege(){
        return utskrivendeLege;
    }

    // Metode som henter pasientens ID
    public int hentPasientId(){
        return pasient.hentId();
    }

    // Metode som henter pasient
    public Pasient hentPasient(){
        return pasient;
    }

    // Metode som henter antall reiterasjoner
    public int hentReit(){
        return reit;
    }

    /* Metode som simulerer bruk av resepten.
    Er det ikke mer igjen på resepten returneres 
    false, om det er mer igjen returneres true, 
    og reit minker med 1 */
    public boolean bruk(){
        boolean tf;
        if (reit <= 0){
            reit = 0;
            tf = false;
        }else{
            tf = true;
        }
        --reit;
        return tf;
    }

    /* Metode som henter reseptens farge
    via arv fra subklassen */
    abstract public String farge();

    // Metode som returnerer pris (arv)
    abstract public int prisAaBetale();

    @Override 
    public String toString(){
        String spes = "";
        if (utskrivendeLege instanceof Spesialist){
            spes = " (Spesialist)";
        } 
        return "\n\nRESEPT\n--------------------------------"
        +"\nFarge: "+farge()
        +"\nResept ID: "+reseptId
        +"\nUtskrivende lege: "+utskrivendeLege.hentNavn()+spes
        +"\nPasient: "+pasient.hentNavn()
        +"\nReseptpris: "+prisAaBetale()+" kr"
        +"\nAntall reiterasjoner: "+reit
        +"\n"+legemiddel.toString()
        +"\n--------------------------------\n\n";
    }
}