class Integrasjonstest {

    // Legemiddel instanser
    static Narkotisk narkLegemiddel = new Narkotisk("Morfin", 3000, 20, 90);
    static Vanedannende vaneLegemiddel = new Vanedannende("Melatonin", 150, 2, 5);
    static Vanlig vanligLegemiddel = new Vanlig("P-piller", 90, 0.5);

    // Lege instanser
    static Lege lege = new Lege("Sandman");
    static Spesialist spesialist = new Spesialist("Jack Frost", "ICE");

    // Resept instanser
    static Hvit hvitResept = new Hvit(narkLegemiddel, lege, 0, 2);
    static Blaa blaaResept = new Blaa(vaneLegemiddel, spesialist, 1, 4);
    static PResept pResept = new PResept(vanligLegemiddel, lege, 2, 6);
    static MilResept mResept = new MilResept(vaneLegemiddel, lege, 3);

    public static void main(String[] args){
        System.out.println("\nLEGEMIDLER\n-------------------------------------------------------------------"
        +narkLegemiddel.toString()+"\n"
        +vaneLegemiddel.toString()+"\n"
        +vanligLegemiddel.toString()+"\n"

        +"\n\nLEGER\n-------------------------------------------------------------------"
        +lege.toString()+"\n"
        +spesialist.toString()+"\n"

        +"\n\nRESEPTER\n-------------------------------------------------------------------"
        +hvitResept.toString()
        +blaaResept.toString()
        +pResept.toString()
        +mResept.toString());
    }
}