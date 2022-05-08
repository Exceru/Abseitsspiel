package aufgabe1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Die Klasse Abseitsspiel beinhaltet alle Spieler und Methoden zum
 *  Start, Ablauf und Abbruch eines Würfel-Abseitsspiel
 */
public class Abseitsspiel {

    private final List<Spieler> spieler;

    /**
     * Der Konstruktor nimmt als Parameter ein Array an Strings, den Spielernamen und
     * erstellt bzw. speichert damit direkt die Instanzen der Spieler in der "spieler" List.
     * Außerdem prüft er ob es genügend Mitspieler (mindestens 2) gibt, andernfalls gibt er eine
     * Fehlermeldung aus. Wenn es genügend Spieler gibt, so werden zudem noch die Spielregeln
     * ausgegeben.
     *
     * @param spielerNamen Beinhaltet ein Array an Strings mit allen Spielernamen.
     */
    public Abseitsspiel(String[] spielerNamen) {
        spieler = new ArrayList<>();

        if(spielerNamen.length > 1) {
            /* Iteriert durch das Namen-Array der Spieler und erstellt zu jedem Spieler eine neue Spieler-Instanz, welche
                in der "spieler" List als ArrayList gespeichert werden. */
            for(String i : spielerNamen) {
                Spieler neuerSpieler = new Spieler(i);
                spieler.add(neuerSpieler);
            }

            System.out.println(this.getSpielRegeln());
        } else {
            throw new IllegalArgumentException("Das Spiel kann aufgrund von zu wenigen Mitspielern nicht konstruiert werden.");
        }

    }


    /**
     * Startet das Abseitsspiel, indem so lange eine neue Runde gestartet wird, wie es erwünscht ist.
     */
    public void starteSpiel() {
        System.out.println("Das Abseitsspiel wird gestartet...\n");
        boolean neueRunde = true;

        // Startet so lange eine neue Runde bis kein Spiel mehr erwünscht wird.
        while(neueRunde) {

            rundeStarten();

            System.out.println("Wird ein neues Spiel erwünscht?");
            int input = de.oop2022.util.UserInterface.requestInt("Wähle 1 für eine neue Runde.\n Wähle 0 zum Abbrechen.",
                                                        0,1);
            if(input == 0 ) { neueRunde = false; }

            // Setze alle Spielerdaten inklusive des "Ausgeschieden" Status zurück.
            spielZuruecksetzen(true);
        }

        System.out.println("Das Spiel wurde beendet.");

    }


    /**
     * Startet eine neue Runde/Spiel. Der Spielverlauf findet in dieser Methode statt.
     */
    private void rundeStarten(){
        // Speichert Index des derzeitigen Spielers.
        int derzeitigerSpieler = 0;

        // Nützlich um den Platz nach dem Ausscheiden zu berechnen.
        int platzCounter = 0;

        int abseitsZahl = generiereAbseitszahl();
        System.out.println("Die Abseitszahl liegt bei " + abseitsZahl + "!\n");

        // Es wird so lange gespielt, wie es noch keinen Gewinner gibt (-1).
        while(ermittleGewinner() == -1) {

            // Ein Spieler darf nur würfeln, wenn er noch nicht ausgeschieden ist.
            if(!spieler.get(derzeitigerSpieler).isAusgeschieden()){

                // Der Lesbarkeit halber wird der Name in einer Variable gespeichert.
                String spielerName = spieler.get(derzeitigerSpieler).getName();
                System.out.println(spielerName + " ist am Zug und würfelt eine...");

                // Der Enter Input zum würfeln wird verlangt.
                de.oop2022.util.UserInterface.requestUserPressReturn();

                int augenzahl = Wuerfel.wuerfeln();
                System.out.println(augenzahl);

                // Die neue Augensumme wird gesetzt und auf dem Bildschirm ausgegeben
                spieler.get(derzeitigerSpieler).setSumme(spieler.get(derzeitigerSpieler).getSumme() + augenzahl);
                System.out.println("Die Augensumme von " + spielerName + " liegt nun bei "
                        + spieler.get(derzeitigerSpieler).getSumme() + ".");

                // Prüfe nun ob der Spieler noch im Spiel ist, oder ausgeschieden wird.
                if(spieler.get(derzeitigerSpieler).getSumme() > 30) {
                    System.out.println("Damit scheidet " + spielerName + " aus.");

                    // Falls es noch keinen Gewinner gibt, so wird eine neue Runde gestartet.
                    if (ermittleGewinner() != -1) {
                        System.out.println("Die anderen starten eine neue Runde.");
                    }

                    System.out.println("-----------------------------------------------------------------------------\n");

                    // Der Spieler scheidet aus und das Spiel wird für alle anderen zurückgesetzt.
                    spieler.get(derzeitigerSpieler).setAusgeschieden(true);

                    // Der Platz des Spielers wird berechnet und der Counter wird iteriert.
                    int platz = spieler.size() - platzCounter;
                    platzCounter += 1;

                    spieler.get(derzeitigerSpieler).setPlatz(platz);
                    spielZuruecksetzen(false);

                } else {
                    System.out.println("Das Spiel geht weiter.\n\n");
                }
            }

            // Der Index des nächsten Spielers wird berechnet
            derzeitigerSpieler = (derzeitigerSpieler + 1) % spieler.size();
        }

        // Am Ende eines Spiels gibt es stets einen eindeutigen Gewinner.
        System.out.println("*** " + spieler.get(ermittleGewinner()).getName() + " hat das Spiel gewonnen! ***");
        // Die Spielauswertung, also die Plätze der Mitspieler werden ausgegeben.
        System.out.println("Spielauswertung:");
        System.out.println(getSpielAuswertung());

    }


    /**
     * @return Eine kurze Beschreibung des Spiels und der Spielregeln als Text.
     */
    private String getSpielRegeln() {
        return ("""
                Das Abseitsspiel:
                Mehrere Spieler würfeln hintereinander. Ziel des Spiels ist es, als Augensumme eine festgelegte Zahl (Abseits) nicht zu überschreiten.
                Wenn ein Spieler die festgelegte Abseitszahl überschreitet, so scheidet dieser aus und die anderen Spieler beginnen eine neue Runde.
                Der oder die Spieler:in, welche:r als letztes verbleibt, gewinnt das Spiel.
                """);
    }

    /**
     * Setzt von jedem Spieler die Spieldaten zurück. (Summe, Ausgeschieden)
     * @param hardReset Aktiviert den vollständigen Reset, also inklusive des "ausgeschieden" Status und das Zurücksetzen des
     *                 erreichten Platzes.
     */
    private void spielZuruecksetzen(boolean hardReset){
        for(Spieler i : spieler) {
            i.setSumme(0);
            if(hardReset) {
                i.setAusgeschieden(false);
                i.setPlatz(1);
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


    /**
     * @return Spielauswertung, mit den erreichen Plätzen der jeweiligen Spieler.
     */
    private String getSpielAuswertung(){
        String[] spielerNamen = new String[spieler.size()];

        // Plätze der jeweiligen Spieler werden ermittelt und kommen an die richtige Stelle im Namen-Array.
        for (Spieler spieler : spieler) {
            spielerNamen[spieler.getPlatz() - 1] = spieler.getName();  // -1, da Array bei 0 beginnt.
        }

        StringBuilder auswertung = new StringBuilder();

        // Der String wird mithilfe des sortierten Namen-Arrays und einem StringBuilder erstellt.
        for(int i = 0; i < spielerNamen.length; i++){
            auswertung.append(i + 1).append(". Platz:  ").append(spielerNamen[i]).append("\n");
        }

        return auswertung.toString();
    }

}
