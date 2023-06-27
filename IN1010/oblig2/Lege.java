class Lege {
    // Instansvariabel
    protected final String navnLege;

    // Konstruktør
    public Lege(String n){
        navnLege = n;
    }

    public String hentNavn(){
        return navnLege;
    }

    @Override
    public String toString(){
        return "\nLEGE\n-------------"
        +"\n Navn: "+navnLege;
    }
}