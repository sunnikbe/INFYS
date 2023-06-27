import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GameOfLifeGUI {

    // Jeg vil at levende celler skal være grønne knapper 
    // og døde hvite

    static int rader = 20;
    static int kolonner = 20;

    static Verden verden = new Verden(rader, kolonner);
    // boolean som sier om game of life kjører
    static boolean kjorer = false;
    static JButton startButton;
    static JButton stopButton;
    static Timer timer;
    static JLabel antLevende;


    private static void oppdaterRutenettGUI(JButton[][] ruter, Rutenett rutenett){
        for (int i = 0; i < ruter.length; i++) {
            for (int j = 0; j < ruter[i].length; j++) {
                if (rutenett.hentCelle(i, j).erLevende()) {
                    ruter[i][j].setBackground(Color.GREEN);
                } else {
                    ruter[i][j].setBackground(Color.WHITE);
                }
            }
        }
        // oppdaterer antall levende når rutenettet blir oppdatert
        antLevende.setText("Antall levende celler: " + rutenett.antallLevende());
    }
    
    public static void main(String[] args){
        Rutenett rutenett = verden.hentRutenett();
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) { System.exit(1); }
        JFrame vindu = new JFrame("Game Of Life!");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        vindu.add(panel);

        JPanel rutenettGUI = new JPanel();
        rutenettGUI.setLayout(new GridLayout(rader, kolonner));
        JButton[][] ruter = new JButton[rader][kolonner];
        for (int rx = 0;  rx < rader;  ++rx) {
            for (int kx = 0;  kx < kolonner;  ++kx) {
            JButton b = new JButton(" ");
            ruter[rx][kx] = b;
            
            if (rutenett.hentCelle(rx, kx).erLevende()){
                b.setBackground(Color.GREEN);
            } else {
                b.setBackground(Color.WHITE);
            }

            class Knappetrykk implements ActionListener {
                int rad;
                int kolonne;
                JButton knapp;
                Rutenett rutenett;

                Knappetrykk (int r, int k, JButton b, Rutenett rutenett){
                    rad = r;
                    kolonne = k;
                    knapp = b;
                    this.rutenett = rutenett;
                }

                @Override
                public void actionPerformed (ActionEvent e){
                    if (knapp.getBackground() == Color.GREEN) {
                        knapp.setBackground(Color.WHITE);
                        rutenett.hentCelle(rad, kolonne).settDoed();
                    } else {
                        knapp.setBackground(Color.GREEN);
                        rutenett.hentCelle(rad, kolonne).settLevende();
                    }
                    oppdaterRutenettGUI(ruter, rutenett);
                }
            }
            b.addActionListener(new Knappetrykk(rx, kx, b, rutenett));
            rutenettGUI.add(b);
            }
        }
        panel.add(rutenettGUI, BorderLayout.CENTER);

        JPanel knappPanel = new JPanel(new BorderLayout());

        // Legg til Start-knapp
        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                stopButton.setEnabled(true);
                kjorer = true;
                
                // vent 0.3 sek fordi 2 sek gikk for sakte 
                // (Thread.sleep funket for en eller annen grunn ikke når jeg kjørte)
                timer = new Timer(300, new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        verden.oppdatering();
                        oppdaterRutenettGUI(ruter, rutenett);
                    }
                });
                timer.start();
            }
        });

        knappPanel.add(startButton, BorderLayout.WEST);

        // Legg til stopp-knapp
        stopButton = new JButton("Stopp");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                kjorer = false;
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                timer.stop();
            }
        });
        knappPanel.add(stopButton, BorderLayout.EAST);

        // Legger til antall levende celler i knappPanel:
        antLevende = new JLabel("Antall levende celler: " + rutenett.antallLevende());
        antLevende.setHorizontalAlignment(JLabel.CENTER);
        knappPanel.add(antLevende, BorderLayout.CENTER);

        panel.add(knappPanel, BorderLayout.NORTH);

        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);

}
}