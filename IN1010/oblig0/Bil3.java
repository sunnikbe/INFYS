class Bil3 {
    private static String bilnummer;

    public Bil3(String n){
        bilnummer = n;
    }

    public static void SkrivUt(){
        System.out.println("Bilnummer: " + Bil3.bilnummer);
    }

    public static String hentNummer(){
        return Bil3.bilnummer;
    }
    
    public static void main(String[] args){
        SkrivUt();
    }
}
