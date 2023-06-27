public class PResept extends Hvit {

    // Konstruktør
    public PResept(Legemiddel l, Lege uL, int p, int r){
        super(l, uL, p, r);
    }

    // Metode som returnerer pris
    public int prisAaBetale(){
        int nyPris = legemiddel.hentPris() - 108;
        if (nyPris < 0){
            return 0;
        } else {
            return nyPris;
        }
    }
}