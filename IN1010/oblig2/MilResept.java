class MilResept extends Hvit {

    // Konstruktør som setter reit til 3
    public MilResept(Legemiddel l, Lege uL, int p){
        super(l, uL, p, 3);
    }

    // Metode som returnerer pris
    public int prisAaBetale(){
        return 0;
    }
}