abstract class Rute{
    public int rad;
    public int kolonne;
    public Rute[] naboer;
    public Labyrint labyrint;

    public Rute(int r, int k, Labyrint lab){
        rad = r;
        kolonne = k;
        labyrint = lab;

        naboer = new Rute[4];
    }

    public void leggTilNaboer(){
        int rader = labyrint.r;
        int kolonner = labyrint.k;

        if (rad > 0){
            naboer[0] = labyrint.ruter[rad - 1][kolonne];
        }

        if (kolonne > 0){
            naboer[1] = labyrint.ruter[rad][kolonne - 1];
        }

        if (rad < rader - 1){
            naboer[2] = labyrint.ruter[rad + 1][kolonne];
        }

        if (kolonne < kolonner - 1){
            naboer[3] = labyrint.ruter[rad][kolonne + 1];
        }
    }

    // Finn utvei ved rekursjon
    public void finn(Rute fra){
        for (int i = 0; i < naboer.length; i++){
            if (naboer[i] != null && (fra == null || naboer[i] != fra)){
                // Hvis ruten vi starter fra er null, skal
                // finn kalles på alle naboer (alle veier videre)
                // ikke kall finn på ruten vi kommer fra
                naboer[i].finn(this);
            }
        }
    }
}