class Aapning extends HvitRute {
    public Aapning(int r, int k, Labyrint lab){
        super(r, k, lab);
    }

    @Override
    public void finn(Rute fra){
        System.out.println("("+ super.rad + "," + super.kolonne + ")");
    }
}