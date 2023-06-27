class Subsekvens {
    // Teller antall subsekvenser
    public final String subsekvens;
    private int antall = 0;

    Subsekvens(String s){
        subsekvens = s;
        // Teller for hver subsekvens som blir laget
        ++antall;
    }

    public int HentAntall(){
        return antall;
    }

    public void EndreAntall(int a){
        antall = a;
    }

    public String toString(){
        return "(" + subsekvens + "," + antall + ")";
    }

    // public static void main(String[] args){
    //     Subsekvens test = new Subsekvens("ABC");
    //     System.out.println(test.toString());
    //     test.EndreAntall(3);
    //     System.out.println(test.toString());
    // }
}