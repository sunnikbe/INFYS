class Bil2 {
    /* Hver bil har en privat string 
    som ikke skal endres (static) */
    private static String bilnummer;

    /* Konstruktør n gir hvert bilobjekt
    oprettet av programmet BilBruk2
     en string bilnummer */
    public Bil2(String n){
        bilnummer = n;
    }

    // Metode som skriver ut bilnummer
    public static void SkrivUt(){
        System.out.println("Bilnummer: " + Bil2.bilnummer);
    }
    
    // Main-metoden kaller metoden
    public static void main(String[] args){
        SkrivUt();
    }
}
