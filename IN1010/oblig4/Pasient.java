class Pasient {
    protected final String navn;
    protected final String foedselsnummer;
    protected final int pasId;
    protected static int pasIdCounter;
    protected IndeksertListe<Resept> resepter = new IndeksertListe<Resept>();

    public Pasient(String n, String f) {
        navn = n;
        foedselsnummer = f;
        pasId = pasIdCounter;
        ++pasIdCounter;
    }

    public int hentId(){
        return pasId;
    }

    public String hentNavn(){
        return navn +" ("+foedselsnummer+")";
    }

    public void leggTilResept(Resept resept){
        resepter.leggTil(resept);
    }

    public Resept hentResept(int resnr){
        Resept ut = resepter.hent(resnr);
        return ut;
    }

    public IndeksertListe<Resept> hentResepter(){
        return resepter;
    }

    public void skrivUtResepter(){
        System.out.println("Liste over "+navn+" sine resepter:");
        for (int i = 0; i < resepter.stoerrelse(); i++){
            Resept resept = resepter.hent(i);
            System.out.println("Reseptnr. :" + i);
            System.out.println(resept.toString());        
        }
    }

    @Override
    public String toString(){
        return "\nNavn: "+navn
        +"\nFødselsnummer: "+foedselsnummer
        +"\nPasient Id: "+hentId()+"\n";
    }
}