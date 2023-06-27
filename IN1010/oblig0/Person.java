public class Person {
    private static String navn;
    private static Bil3 minBil;

    public Person(String na, Bil3 bil){
        navn = na;
        Person.minBil = bil;
    }

    public static void printInfo(){
        System.out.println("Navn:" + Person.navn);
        System.out.println("Bilnummer:" + Person.minBil.hentNummer());
    }

    public static void main(String[] args){
        printInfo();
    }
}
