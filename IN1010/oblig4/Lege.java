class Lege implements Comparable<Lege> {
    // Instansvariabler 
    protected final String navnLege;
    protected IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<Resept>();

    // Konstruktør
    public Lege(String n){
        navnLege = n;
        
    }

    public int compareTo(Lege lege){
        return navnLege.compareTo(lege.hentNavn());
    }

    public String hentNavn(){
        return navnLege;
    }

    public Resept skrivResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        Resept resept = new Hvit(legemiddel, this, pasient, reit); 
        return resept;
    }

    public void leggTilResept(Resept resept){
        utskrevneResepter.leggTil(resept);
    }

    public IndeksertListe<Resept> hentResepter(){
        return utskrevneResepter;
    }

    public void skrivUtResepter(){
        System.out.println(navnLege+" sine utskrevne resepter:");
        for (int i = 0; i < utskrevneResepter.stoerrelse(); i++){
            Resept resept = utskrevneResepter.hent(i);
            System.out.println("Reseptnr. :" + i);
            System.out.println(resept.toString());
        }
    }

    @Override
    public String toString(){
        return "\n\nLEGE\n-------------"
        +"\n Navn: "+navnLege+"\n";
    }
}