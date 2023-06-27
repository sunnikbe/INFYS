class SortRute extends Rute{

    public SortRute(int r, int k, Labyrint lab){
        super(r, k, lab);
    }

    @Override 
    public void finn(Rute fra){
        // denne ruten er ingen åpning eller
        // vei videre, ikke gjør noenting
        return;
    }

    @Override
    public String toString(){
        return "|X|";
    }
}