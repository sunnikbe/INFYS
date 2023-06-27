class Narkotisk extends Legemiddel{
    // Spesifikt for narkotiske legemidler
    public final int styrke;

    public Narkotisk(String n, int p, double v, int s){
        super(n, p, v);
        styrke = s;
    }
    
    @Override 
    public String toString(){
        return  super.toString() + "\n Styrke: "+styrke;
    }
}
