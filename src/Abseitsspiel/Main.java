package Abseitsspiel;


/**
 * Die Main Klasse beinhaltet die Funktionalit&auml;t zum Erstellen und Ausf&uuml;hren des Spiels.
 */
public class Main {

    /**
     * Main Methode des Programmes, in welcher das Absseitsspiel erstellt und gestartet wird.
     * @param args Programmargumente
     */
    public static void main(String[] args) {
        String[] namen = {"Heisenberg", "Jessie"/*"Mike", "Jimmy", "Gus", "Tuco"*/};

        Abseitsspiel abseitsspiel = new Abseitsspiel(namen);

        abseitsspiel.starteSpiel();
    }
}
