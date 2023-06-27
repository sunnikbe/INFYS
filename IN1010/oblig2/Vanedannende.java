class Vanedannende extends Legemiddel{
    // Spesifikt for vanedannende legemidler
    public final int styrke;

    public Vanedannende(String n, int p, double v, int s){
        super(n, p, v);
        styrke = s;
    }

    @Override 
    public String toString(){
        return super.toString() + "\n Styrke: "+styrke;
    }
}
