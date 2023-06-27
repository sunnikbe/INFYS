public class Celle {
    // Initierer statusvariabel
    boolean levende;
    // Instansvariabler
    Celle[] naboer;
    int antNaboer;
    int antLevendeNaboer;

    /* Konstruktør som setter statusvariabel 
    til død, naboer til en array med 
    8 elementer, antNaboer til 0 og 
    antLevendeNaboer til 0 */
    public Celle(){
        levende = false;
        naboer = new Celle[8];
        antNaboer = 0;
        antLevendeNaboer = 0;
    }

    // Metode som setter status til død
    public void settDoed() {
        levende = false;
    }

    // Metode som setter status til levende
    public void settLevende() {
        levende = true;
    }

    // Metode som returnerer cellens status
    public boolean erLevende() {
        if (levende == true) {
            return true;
        } else {
            return false;
        }
    }

    /* Metode som returnerer 
    tegnrepresentasjon til cellens status */
    public char hentStatusTegn() {
        if (levende == true) {
            return 'O';
        } else {
            return '.';
        }
    }

    /* Metode som legger en nabo 
    (instans av klassen Celle) inn i 
    arrayen naboer og øker antNaboer */
    public void leggTilNabo(Celle nabo) {
        naboer[antNaboer] = nabo;
        if (nabo != null){
            ++antNaboer;
        }
    }

    /* Metode som teller antall levende 
    naboer og oppdaterer instansvariabelen 
    antLevendeNaboer */
    public void tellLevendeNaboer() {
        antLevendeNaboer = 0;
        for (int i = 0; i < antNaboer; i++) {
            if (naboer[i].erLevende() == true) {
                ++antLevendeNaboer; 
            }
        }
    }

    /* Metode som oppdaterer status basert på
    Game of Life reglene */
    public void oppdaterStatus() {
        if (levende == false) {
            if (antLevendeNaboer == 3) {
                settLevende();
            }
        } else if (antLevendeNaboer < 2 || antLevendeNaboer > 3) {
                settDoed();
            }
        }
}