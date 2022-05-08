package Abseitsspiel;
/** Spielerklasse f&uuml;r das Abseitsspiel, welche die Daten und Methoden eines jeden Spielers enth&auml;lt.*/
public class Spieler {
    /**
     * Speichert den Namen des Spielers.
     */
    private final String name;
    /**
     * Entscheidet darueber, ob der Spieler im Spiel ausgeschieden ist oder noch mitspielen darf.
     */
    private boolean ausgeschieden;
    /**
     * Speichert die Augensumme des Spielers.
     */
    private int summe;
    /**
     * Speichert nach Ausscheiden den Platz des Spielers.
     */
    private int platz;

    /** Der Konstruktur Spieler() nimmt als Parameter einen String, um den Namen des Spielers zu setzen.
     * @param name Setzt Spielername.*/
    public Spieler(String name){
        this.name = name;
        this.ausgeschieden = false;
        this.summe = 0;
        this.platz = 1;
    }

    /** Gibt Auskunft &uuml;ber den Namen des Spielers.
     * @return Name des Spielers. */
    public String getName() {
        return name;
    }

    /** Gibt Auskunft dar&uuml;ber, ob der Spieler bereits ausgeschieden ist.
     * @return Status im Spiel.*/
    public boolean isAusgeschieden() {
        return ausgeschieden;
    }

    /** Nimmt als Parameter einen Boolean, um den &quot;Ausgeschieden&quot; Status zu ver&auml;ndern.
     * @param ausgeschieden Setzt den Spielerstatus.*/
    public void setAusgeschieden(boolean ausgeschieden) {
        this.ausgeschieden = ausgeschieden;
    }

    /** Gibt Auskunft &uuml;ber die W&uuml;rfelsumme des Spielers.
     * @return Die W&uuml;rfelsumme als int des Spielers.*/
    public int getSumme() {
        return summe;
    }

    /** Nimmt als Parameter eine Ganzzahl, um die W&uuml;rfelsumme zu ver&auml;ndern.
     * @param summe W&uuml;rfelsumme wird gesetzt. */
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
