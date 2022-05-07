package aufgabe1;

import java.util.Random;

/**
 * Allgemeine Klasse für den Funktionsumfang eines Würfels. */
public class Wuerfel {
    /** Generiert und gibt eine zufällige Ganzzahl zwischen 1 - 6 zurück.
     * @return Zufälliger int zwischen 1 bis 6.*/
    public static int wuerfeln() {
        Random random = new Random();

        return random.nextInt(6) + 1;
    }
}
