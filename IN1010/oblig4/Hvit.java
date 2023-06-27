class Hvit extends Resept {
    public final String hvit = "hvit";

    // Konstruktør
    public Hvit(Legemiddel l, Lege uL, Pasient p, int r){
        super(l, uL, p, r);
    }

    // Metode som returnerer farge
    public String farge(){
        return hvit;
    }

    // Metode som returnerer pris
    public int prisAaBetale(){
        return legemiddel.hentPris();
    }
}