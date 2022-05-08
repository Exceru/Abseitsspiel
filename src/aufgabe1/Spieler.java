package aufgabe1;
/** Spielerklasse für das Abseitsspiel, welche die Daten und Methoden eines jeden Spielers enthält.*/
public class Spieler {
    private final String name;
    private boolean ausgeschieden;
    private int summe;
    private int platz;

    /** Der Konstruktur Spieler() nimmt als Parameter einen String, um den Namen des Spielers zu setzen.
     * @param name Setzt Spielername.*/
    public Spieler(String name){
        this.name = name;
        this.ausgeschieden = false;
        this.summe = 0;
        this.platz = 1;
    }

    /** Gibt Auskunft über den Namen des Spielers.
     * @return Name des Spielers. */
    public String getName() {
        return name;
    }

    /** Gibt Auskunft darüber, ob der Spieler bereits ausgeschieden ist.
     * @return Status im Spiel.*/
    public boolean isAusgeschieden() {
        return ausgeschieden;
    }

    /** Nimmt als Parameter einen Boolean, um den "Ausgeschieden" Status zu verändern.
     * @param ausgeschieden Setzt den Spielerstatus.*/
    public void setAusgeschieden(boolean ausgeschieden) {
        this.ausgeschieden = ausgeschieden;
    }

    /** Gibt Auskunft über die Würfelsumme des Spielers.
     * @return Die Würfelsumme als int des Spielers.*/
    public int getSumme() {
        return summe;
    }

    /** Nimmt als Parameter eine Ganzzahl, um die Würfelsumme zu verändern.
     * @param summe Würfelsumme wird gesetzt. */
    public void setSumme(int summe) {
        this.summe = summe;
    }

    /**
     * @return Gibt den erreichten Platz im Spiel aus.
     */
    public int getPlatz() {
        return platz;
    }

    /**
     * @param platz Setzt den erreichten Platz im Spiel.
     */
    public void setPlatz(int platz) {
        this.platz = platz;
    }
}
