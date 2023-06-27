class Blaa extends Resept {
    public int nyPris;
    public double prosent = 0.25;
    public final String blaa = "blaa"; 

    // Konstruktør
    public Blaa(Legemiddel l, Lege uL, int p, int r){
        super(l, uL, p, r);
    }

    // Metode som returnerer farge
    public String farge(){
        return blaa;
    }

    // Metode som returnerer pris
    public int prisAaBetale(){
        /* Returnerer pris å betale til 25% 
        av opprinnelig pris */
        nyPris = (int)Math.round(legemiddel.hentPris()*prosent);
        return nyPris;
    }
}