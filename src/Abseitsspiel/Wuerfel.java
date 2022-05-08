package Abseitsspiel;

import java.util.Random;

/**
 * Allgemeine Klasse f&uuml;r den Funktionsumfang eines W&uuml;rfels. */
public class Wuerfel {
    /** Generiert und gibt eine zuf&auml;llige Ganzzahl zwischen 1 - 6 zur&uuml;ck.
     * @return Zuf&auml;lliger int zwischen 1 bis 6.*/
    public static int wuerfeln() {
        Random random = new Random();

        return random.nextInt(6) + 1;
    }
}
