class TestResepter {
    // Metoder til å bruke inne i testene
    private static void printTest(String testnavn, int forventet, int resultat){
        if (forventet != resultat){
            System.out.println("Noe er feil");
        }
        System.out.println(testnavn+ ":\nForventet "+forventet+"\nResultat "+resultat+"\n");
    }

    private static void fargeTest(String hviteBlaa, String farge){
        System.out.println("Den "+hviteBlaa+" resepten har fargen "+farge+"\n");
    }

    private static void reitTest(Resept resept, boolean bruk){
        System.out.println("Test av reiterasjoner:");
        if (bruk == true && resept.hentReit() == 0){
            System.out.println("Noe er feil. Boolean bruk er true og reit = 0.");
        }
        if (bruk == true){
            System.out.println("Det er "+resept.hentReit()+" reiterasjoner igjen."+"\n");
        } else {
            System.out.println("Resepten er tom. (Reit = "+resept.hentReit()+")\n");
        }
    }

    // Testmetoder
    private static void TestHvit(){
        Vanlig l1 = new Vanlig("paracet", 120, 30);
        Lege utskrivendeL1 = new Lege("Mamma");
        Hvit resept1 = new Hvit(l1, utskrivendeL1, 0, 2);

        int forventetID = 0;
        int resept1Id = resept1.hentId();
        printTest("Hvit resept ID", forventetID, resept1Id);
        fargeTest("hvite", resept1.farge());
        System.out.println("\n");
    } 

    private static void TestBlaa(){
        Vanedannende l2 = new Vanedannende("Melatonin", 300, 20, 10);
        Lege utskrivendeL2 = new Lege("Sandman");
        Blaa resept2 = new Blaa(l2, utskrivendeL2, 0, 4);

        int forventetPris = (int)Math.round(300*0.25);
        printTest("Blå resept pris", forventetPris, resept2.prisAaBetale());
        fargeTest("blaa", resept2.farge());
        System.out.println("\n");
    }

    private static void TestPResept(){
        Vanlig l3 = new Vanlig("P-piller", 200, 10);
        Lege utskrivendeL3 = new Lege("Helsesister");
        PResept resept3 = new PResept(l3, utskrivendeL3, 0, 7);

        int forventetReit = 7;
        printTest("PResept reiterasjoner", forventetReit, resept3.hentReit());

        int forventetPris = l3.hentPris() - 108;
        printTest("Pris PResept", forventetPris, resept3.prisAaBetale());

        // Ny pris under 108
        int forventetNullPris = 0;
        l3.settNyPris(100);
        printTest("Pris PResept", forventetNullPris, resept3.prisAaBetale());

        fargeTest("hvite P", resept3.farge());
        System.out.println("\n");
    }

    private static void TestMilResept(){
        Narkotisk l4 = new Narkotisk("Fentanyl", 500, 50, 100);
        Lege utskrivendeL4 = new Lege("MilLegen");
        MilResept resept4 = new MilResept(l4, utskrivendeL4, 0);

        System.out.println(resept4.toString());

        fargeTest("hvite Mil", resept4.farge());

        printTest("Pris MilResept", 0, resept4.prisAaBetale());
        printTest("Sjekker at legemiddelets pris ikke er endret", 500, l4.hentPris());

        // Test at reit = 3 og at den teller ned etter bruk
        printTest("Reiterasjoner MilResept", 3, resept4.hentReit());
        reitTest(resept4, resept4.bruk());
        printTest("Reiterasjoner etter 1 bruk", 2, resept4.hentReit());

        // Bruk opp og test
        reitTest(resept4, resept4.bruk());
        printTest("Reiterasjoner etter 2 bruk", 1, resept4.hentReit());
        reitTest(resept4, resept4.bruk());
        printTest("Reiterasjoner etter 3 bruk", 0, resept4.hentReit());
        // Prøv å bruke enda en gang
        reitTest(resept4, resept4.bruk());
        System.out.println("\n");
    }

    public static void main(String[] args){
        TestHvit();
        TestBlaa();
        TestPResept();
        TestMilResept();
    }
}