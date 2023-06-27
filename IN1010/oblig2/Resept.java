abstract class Resept {
    // Instansvariabler felles for resepter
    protected final int reseptId;
    protected static int resIdCounter;
    protected final Legemiddel legemiddel;
    protected final Lege utskrivendeLege;
    protected final int pasientId;
    protected int reit;

    // Konstruktør
    public Resept(Legemiddel l, Lege uL, int p, int r){
        legemiddel = l;
        utskrivendeLege = uL;
        pasientId = p;
        reit = r;
        reseptId = resIdCounter;
        ++resIdCounter;
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
        return pasientId;
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
        --reit;
        if (reit <= 0){
            reit = 0;
            return false;
        }else{
            return true;
        }
    }

    /* Metode som henter reseptens farge
    via arv fra subklassen */
    abstract public String farge();

    // Metode som returnerer pris (arv)
    abstract public int prisAaBetale();

    @Override 
    public String toString(){
        return "\nRESEPT\n--------------------------------"
        +"\nFarge: "+farge()
        +"\nResept ID: "+reseptId
        +"\nUtskrivende lege: "+utskrivendeLege.hentNavn()
        +"\nPasient ID: "+pasientId
        +"\nReseptpris: "+prisAaBetale()+" kr"
        +"\nAntall reiterasjoner: "+reit
        +"\n"+legemiddel.toString()
        +"\n--------------------------------\n";
    }
}