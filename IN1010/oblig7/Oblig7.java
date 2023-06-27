import java.util.Scanner;

public class Oblig7 {

    public static int[] velgStartRute() {
        Scanner myObj1 = new Scanner(System.in);
        System.out.println("\nSkriv inn ønsket startrute [rad, kolonne]: \n"+
        "(For å avslutte skriv 'a')");
        int rad = myObj1.nextInt();
        int kolonne = myObj1.nextInt();

        return new int[] {rad, kolonne};
    }

    public static void main(String[] args){
        try {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Vennligst oppgi filen du ønsker å oprette en labyrint fra: ");
            String filnavn = myObj.nextLine();

            Labyrint lab = new Labyrint(filnavn);
            System.out.println("Labyrinten ser slik ut:");
            System.out.println(lab);

            try {

                // kommandoløkke her!!
                while (true){

                    int[] rk = velgStartRute();

                    if (lab.ruter[rk[0]][rk[1]] instanceof SortRute){
                        System.out.println("\nKan ikke starte i en sort rute, prøv igjen.");
                    } else {
                        System.out.println("\nLabyrinten med angitt startposisjon 'S': ");
                        lab.skrivUtMedStart(rk[0], rk[1]);
                        System.out.println("\nÅpninger:");
                    }
                    lab.finnUtveiFra(rk[0], rk[1]);
                }

            } catch (Exception e){
                System.out.println("Program avsluttet.");
            }

            myObj.close();
        } catch (Exception e){
            System.out.println("Sjekk at du har skrevet inn filnavnet riktig.");
        }
    }
}
