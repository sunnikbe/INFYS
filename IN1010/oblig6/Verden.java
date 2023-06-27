public class Verden {
    // Instansvariabler
    Rutenett rutenett;
    int genNr;

    /* Konstruktør som tar antall rader og kolonner 
    som input og oppretter en instans av klassen 
    Rutenett med ant rader og kolonner som lagres i 
    instansvariabelen rutenett.
    Fyller også rutenettet med celler og kobler de sammen */
    public Verden(int r, int k){
        rutenett = new Rutenett(r, k);
        genNr = 0;
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
    }

    /* Metode som tegner rutenettet, skriver ut 
    generasjonsnummeret og antall levende celler */
    public void tegn(){
        System.out.println("Generasjon nr " + genNr + ":");
        rutenett.tegnRutenett();
        System.out.println("\n\nDet er " + rutenett.antallLevende() + " levende celler." + "\n\n\n\n\n\n");
    }

    /* Metode som teller og oppdaterer antallet levende
    naboer for hver celle, oppdaterer hver celles status
    og teller antall generasjoner */
    public void oppdatering(){
        for (int r = 0; r < rutenett.antRader; r++){
            for (int k = 0; k < rutenett.antKolonner; k++){
                rutenett.hentCelle(r, k).tellLevendeNaboer();
            }
        }
        for (int r = 0; r < rutenett.antRader; r++){
            for (int k = 0; k < rutenett.antKolonner; k++){
                rutenett.hentCelle(r, k).oppdaterStatus();
            }
        }
        ++genNr;
    }
    public Rutenett hentRutenett(){
        return rutenett;
    }
}