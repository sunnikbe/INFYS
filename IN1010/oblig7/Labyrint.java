import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint{
    public Rute[][] ruter;
    public String filnavn;
    protected String labyrint = "";
    protected String labMstart = "";

    public int r; // antall rader 
    public int k; // antall kolonner

    public Labyrint(String f){
        filnavn = f;

        // les ant rader og kolonner fra fil!
        try{
            File myObj = new File(filnavn);
            Scanner myReader = new Scanner(myObj);
    
            String[] rk = myReader.nextLine().split(" ");
            r = Integer.parseInt(rk[0]);
            k = Integer.parseInt(rk[1]);

            ruter = new Rute[r][k];

            int i = 0;
            while (myReader.hasNextLine()){
                char[] chars = myReader.nextLine().toCharArray();
                for (int j = 0; j < k; j++){
                    if (chars[j] == '.'){
                        // legg inn åpninger
                        // hvis ruten er hvit og langs kanten er det en åpning
                        if (i == 0 || i == r - 1){
                            ruter[i][j] = new Aapning(i, j, this);
                        } else if (j == 0 || j == k - 1){
                            ruter[i][j] = new Aapning(i, j, this);
                        } else {
                            ruter[i][j] = new HvitRute(i, j, this);
                        } 
                    } else {
                        ruter[i][j] = new SortRute(i, j, this);
                    }
                }
                i++;
            }

            myReader.close();
        } catch (FileNotFoundException e){
            System.out.println("Finner ikke filen.");
        }

        // Legg til naboer etter at alle rutene er 
        // lagt inn i ruter.
        for (int l = 0; l < r; l++){
            for (int m = 0; m < k; m++){
                if (ruter[l][m] != null){
                    ruter[l][m].leggTilNaboer();
                }
            }
        }
    }

    public void finnUtveiFra(int rad, int kol){
        // kallet fra startruten er null slik at 
        // alle veier videre prøves i finn metoden
        ruter[rad][kol].finn(null);
    }

    @Override
    public String toString(){
        for (int i = 0; i < ruter.length; i++){
            labyrint += "\n";
            for (int j = 0; j < ruter[i].length; j++){
                labyrint += ruter[i][j].toString();
            }
        }

        return labyrint + "\n";
    }


    public void skrivUtMedStart(int rad, int kolonne){
        for (int i = 0; i < ruter.length; i++){
            labMstart += "\n";
            for (int j = 0; j < ruter[i].length; j++){
                if (i == rad && j == kolonne){
                    labMstart += " S ";
                } else {
                    labMstart += ruter[i][j].toString();
                }
            }
        }

        System.out.println(labMstart + "\n");
        labMstart = "";
    }
}