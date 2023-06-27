import java.util.Scanner;

public class GameOfLife {
    // Instansvariabler
    Verden verden;
    public static void main(String[] args){
        try {
            Scanner myObj = new Scanner(System.in);
            System.out.print("Velkommen til Game of life! \n" + "Vennligst oppgi ønsket antall rader og kolonner: ");
            int r = myObj.nextInt();
            int k = myObj.nextInt();

            try {
                Scanner myObj1 = new Scanner(System.in);
                System.out.print("Ønsker du å endre antall generasjoner [Ja: skriv inn antall/Nei]? ");
                int generasjoner = myObj1.nextInt();
                Verden verden = new Verden(r, k);
                System.out.println("\nKjører programmet med " + r + " rader og " + k + " kolonner gjennom " + generasjoner + " generasjoner:");
                System.out.println();
                verden.tegn();
                for (int i = 0; i < generasjoner; i++){
                    verden.oppdatering();
                    verden.tegn();
                }
            } catch (Exception e) {
                System.out.println("\nKjører programmet med " + r + " rader og " + k + " kolonner gjennom 3 generasjoner:");
                int generasjoner = 3;
                Verden verden = new Verden(r, k);
                System.out.println();
                verden.tegn();
                for (int i = 0; i < generasjoner; i++){
                    verden.oppdatering();
                    verden.tegn();
                }
            }
        } catch (Exception e) {
            // Programmet kjører med standardinstillingene dersom noe går galt
            System.out.println("\nFeil inntasting, kjører programmet med 8 rader og 12 kolonner gjennom 3 generasjoner:");
            Verden verden = new Verden(8, 12);
            System.out.println();
            verden.tegn();
            for (int i = 0; i < 3; i++){
                verden.oppdatering();
                verden.tegn();
            }
        }
    }
}