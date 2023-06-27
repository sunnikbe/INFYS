class TestLegemiddel {

    private static void testNarkotisk(){
        // Opprett objekt
        String navnN = "Meth";
        int prisN = 2000;
        double virkestoffN = 19;
        int styrkeN = 30;
        Narkotisk legemiddel1 = new Narkotisk(navnN, prisN, virkestoffN, styrkeN);

        // test ID
        int idN = legemiddel1.iD;
        if (idN != 0){
            System.out.println("Noe er feil med ID'en. Den er " + idN + ", men skulle vaert " + 0);
        }else{
            System.out.println("\nID for forste legemiddel er riktig og " + idN);
        }

        // test hentPris metode
        int pris = legemiddel1.hentPris();
        if (pris == prisN){
            System.out.println("Prisen er riktig! " + pris);
        }else{
            System.out.println("Prisen er " + pris + " og skal vaere " + prisN);
        }
        
        // test settNyPris metode
        int nyPris = 2500;
        legemiddel1.settNyPris(nyPris);
        if (legemiddel1.hentPris() == nyPris){
            System.out.println("Prisen er riktig! " + nyPris);
        }else{
            System.out.println("Prisen er " + legemiddel1.hentPris() + " og skal vaere " + nyPris);
        }

        // test navn, virkestoff of styrke
        System.out.println("Navn forste legemiddel: " + navnN);
        System.out.println("Virkestoff: " + legemiddel1.virkestoff + " mg");
        System.out.println("Styrke: " + legemiddel1.styrke);

        // minitest toString()
        System.out.println();
        System.out.println(legemiddel1.toString());
        }

    private static void testiD(){
        // Opprett vanedannende objekt
        String navnV = "Nikotin";
        int prisV = 120;
        double virkestoffV = 0.3;
        int styrkeV = 50;

        // Opprett vanlig objekt
        String navnVa = "Paracet";
        int prisVa = 110;
        double virkestoffVa = 0.8;

        Vanedannende legemiddel2 = new Vanedannende(navnV, prisV, virkestoffV, styrkeV);
        Vanlig legemiddel3 = new Vanlig(navnVa, prisVa, virkestoffVa);

        // test ID
        int idV = legemiddel2.iD;
        if (idV != 1){
            System.out.println("Noe er feil med ID'en. Den er " + idV + ", men skulle vaert " + 1 + "\n");
        }else{
            System.out.println("\nID for andre legemiddel er riktig og " + idV + "\n");
        }

        int idVa = legemiddel3.iD;
        if (idVa != 2){
            System.out.println("Noe er feil med ID'en. Den er " + idVa + ", men skulle vaert " + 2 + "\n");
        }else{
            System.out.println("\nID for forste legemiddel er riktig og " + idVa + "\n");
        }
    }

    public static void main(String[] args){
        testNarkotisk();
        testiD();
    }
}
