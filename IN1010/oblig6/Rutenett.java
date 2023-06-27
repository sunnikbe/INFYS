public class Rutenett {
    // Instansvariabler
    int antRader;
    int antKolonner;
    Celle[][] rutene;

    /* Konstruktør som tar dimensjonene på
    rutenettet som input og lagrer disse i
    instansvariablene */
    public Rutenett(int r, int k){
        antRader = r;
        antKolonner = k;
        // 2D array med elementer av typen Celle
        rutene = new Celle[r][k];
    }

    /* Metode som tar en posisjon i rutene
    som input, oppretter en Celle og legger
    den inn i posisjonen med 33% sannsynlighet
    for å være levende */
    public void lagCelle(int r, int k){
        Celle celle = new Celle();
        rutene[r][k] = celle;
        if (Math.random() <= 0.3333){
            celle.settLevende();
        }
    }

    // Metode som fyller rutenettet med celler
    public void fyllMedTilfeldigeCeller(){
        for (int r = 0; r < antRader; r++){
            for (int k = 0; k < antKolonner; k++){
                lagCelle(r, k);
            }
        }
    }

    /* Metode som tar koordinater i rutene som input 
    og returnerer Celle objektet.
    Returnerer null ved ulovlig indeks */
    public Celle hentCelle(int r, int k){
        if (r < 0 || r >= antRader){
            return null;
        }
        else if (k < 0 || k >= antKolonner){
            return null;
        }
        else {
            return rutene[r][k];
        }
    }

    // Metode som skriver ut rutenettet
    public void tegnRutenett(){
        String linje = "*---";
        String kryss = "*";
        for (int r = 0; r < antRader; r++){
            System.out.println();
            for (int i = 0; i < antKolonner; i++){
                System.out.print(linje);
            }
            System.out.print(kryss);
            System.out.println();
            System.out.print("| ");
            for (int k = 0; k < antKolonner; k++){
                System.out.print(hentCelle(r, k).hentStatusTegn() + " | " );
            }
        }
        System.out.println();
        for (int i = 0; i < antKolonner; i++){
            System.out.print(linje);
        }
        System.out.print(kryss);
    }

    /* Metode som tar en celles koordinater som input og 
    legger til cellene rundt som naboer */
    public void settNaboer(int r, int k){
        for (int i = r - 1; i <= r + 1; i++){
            for (int j = k - 1; j <= k + 1; j++){
                // ikke legg til cellen som nabo
                if (i == r && j == k){
                    continue;
                }
                hentCelle(r, k).leggTilNabo(hentCelle(i, j));
            }
        }
    }

    /* Metode som kaller metoden settNaboer for alle 
    cellene i rutenettet */
    public void kobleAlleCeller(){
        for (int r = 0; r < antRader; r++){
            for (int k = 0; k < antKolonner; k++){
                settNaboer(r, k);
            }
        }
    }

    /* Metode som returnerer antall levende celler totalt 
    for hele rutenettet */
    public int antallLevende(){
        int antLevende = 0;
        for (int r = 0; r < antRader; r++){
            for (int k = 0; k < antKolonner; k++){
                if (hentCelle(r, k).erLevende()){
                    ++antLevende;
                }
            }
        }
        return antLevende;
    }
}