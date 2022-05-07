package aufgabe1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Die Klasse Abseitsspiel beinhaltet alle Spieler und Methoden zum
 *  Start, Ablauf und Abbruch eines Würfel-Abseitsspiel
 */
public class Abseitsspiel {

    private List<Spieler> spieler;

    private int abseitsZahl;

    /**
     * Der Konstruktor nimmt als Parameter ein Array an Strings, den Spielernamen und
     * erstellt bzw. speichert damit direkt die Instanzen der Spieler in der "spieler" List.
     *
     * @param spielerNamen Beinhaltet ein Array an Strings mit allen Spielernamen.
     */
    public Abseitsspiel(String spielerNamen[]) {
        spieler = new ArrayList<Spieler>();

        /* Iteriert durch das Namen-Array der Spieler und erstellt zu jedem Spieler eine neue Spieler-Instanz, welche
         in der "spieler" List als ArrayList gespeichert werden. */
        for(String i : spielerNamen) {
            Spieler neuerSpieler = new Spieler(i);
            spieler.add(neuerSpieler);
        }
    }


    /**
     * Startet das Abseitsspiel, indem so lange eine neue Runde gestartet wird, wie es erwünscht ist.
     */
    public void starteSpiel() {
        System.out.println("Das Abseitsspiel wird gestartet...");
        boolean neueRunde = true;

        // Startet so lange eine neue Runde bis kein Spiel mehr erwünscht wird.
        while(neueRunde) {

            rundeStarten();

            System.out.println("Wird ein neues Spiel erwünscht?");
            int input = de.oop2022.util.UserInterface.requestInt("Wähle 1 für eine neue Runde.\n Wähle 0 zum Abbrechen.",
                                                        0,1);
            if(input == 0 ) { neueRunde = false; }
            if(input == 1 ) { neueRunde = true; }

            // Setze alle Spielerdaten inklusive des "Ausgeschieden" Status zurück.
            spielZuruecksetzen(true);
        }

        System.out.println("Das Spiel wurde beendet.");

    }


    /**
     * Startet eine neue Runde/Spiel. Der Spielverlauf findet in dieser Methode statt.
     */
    private void rundeStarten(){
        // Siehe ermittleGewinner() Methode.
        int gewinner = -1;
        int derzeitigerSpieler = 0;
        this.abseitsZahl = generiereAbseitszahl();
        System.out.println("Die Abseitszahl liegt bei " + this.abseitsZahl + "!\n");

        // Die Spieler spielen so lange, bis es nur noch 1 Mitspieler verbleibt.
        while(ermittleGewinner() == -1) {

            // Ein Spieler darf nur würfeln, wenn er noch nicht ausgeschieden ist.
            if(!spieler.get(derzeitigerSpieler).isAusgeschieden()){
                String spielerName = spieler.get(derzeitigerSpieler).getName();
                System.out.println(spielerName + " ist am Zug und würfelt eine...");

                de.oop2022.util.UserInterface.requestUserPressReturn();
                int augenzahl = Wuerfel.wuerfeln();
                System.out.println(augenzahl);

                spieler.get(derzeitigerSpieler).setSumme(spieler.get(derzeitigerSpieler).getSumme() + augenzahl);
                System.out.println("Die Augensumme von " + spielerName + " liegt nun bei "
                        + spieler.get(derzeitigerSpieler).getSumme() + ".");

                // Prüfe nun ob der Spieler noch im Spiel ist, oder ausgeschieden wird.
                if(spieler.get(derzeitigerSpieler).getSumme() > 30) {
                    System.out.println("Damit scheidet " + spielerName + " aus.");
                    if (ermittleGewinner() != -1) {
                        System.out.println("Die anderen starten eine neue Runde.");

                    }
                    System.out.println("-----------------------------------------------------------------------------\n");
                    spieler.get(derzeitigerSpieler).setAusgeschieden(true);
                    spielZuruecksetzen(false);
                } else {
                    System.out.println("Das Spiel geht weiter.\n");
                }
            }

            derzeitigerSpieler = (derzeitigerSpieler + 1) % spieler.size();
        }

        System.out.println("*** " + spieler.get(ermittleGewinner()).getName() + " hat das Spiel gewonnen! ***");

    }


    /**
     * @return Eine kurze Beschreibung des Spiels und der Spielregeln als Text.
     */
    private String getSpielRegeln() {
        return "Platzhalter";
    }

    /**
     * @param spieler Fügt diesen Spieler in die "spieler" List ein.
     */
    private void spielerHinzufuegen(Spieler spieler) {

    }


    /**
     * Setzt von jedem Spieler die Spieldaten zurück. (Summe, Ausgeschieden)
     * @param hardReset Aktiviert den vollständigen Reset, also inklusive des "ausgeschieden" Status.
     */
    private void spielZuruecksetzen(boolean hardReset){
        for(Spieler i : spieler) {
            i.setSumme(0);
            if(hardReset) {
                i.setAusgeschieden(false);
            }
        }
    }


    /**
     * @return Gibt einen zufälligen int zwischen 30 und 50 zurück.
     */
    private int generiereAbseitszahl(){
        Random random = new Random();
        return random.nextInt(31) + 20;
    }


    /**
     * @return Gibt den Index des Gewinners aus der spieler List zurück.
     * Wenn es keinen Gewinner gibt, wird -1 zurückgegeben.
     */
    private int ermittleGewinner(){
        int anzahlAktiveSpieler = 0;
        int gewinnerIndex = -1;

        // Iteriert durch die Spieler Liste um die noch nicht ausgeschiedenen Spieler zu zählen und
        // einen potenziellen Gewinner zu finden.
        for(int i = 0; i < spieler.size(); i++) {
            if(!spieler.get(i).isAusgeschieden()) {
                anzahlAktiveSpieler++;
                gewinnerIndex = i;
            }
        }

        // Gebe -1 aus wenn mehr als ein aktiver Spieler gefunden wurde.
        if(anzahlAktiveSpieler > 1) {
            gewinnerIndex = -1;
        }

        return gewinnerIndex;
    }



}
