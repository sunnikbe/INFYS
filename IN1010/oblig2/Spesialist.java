class Spesialist extends Lege implements Godkjenningsfritak {
    public final String kontrollkode;

    // Konstruktør
    public Spesialist(String n, String k){
        super(n);
        kontrollkode = k;
    }

    @Override
    public String hentKontrollkode(){
        return kontrollkode;
    }

    @Override
    public String toString(){
        return super.toString()
        +"\n Legen er spesialist"
        +"\n Kontrollkode: "+kontrollkode;
    }
}