package de.oop2022.util;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Diese Klasse stellt Methoden f&uuml;r die Nutzung von Konsoleneingaben im
 * Praktikum Objektorientierte Programmierung 2022 bereit.
 *
 * @author Nicolas Handke
 * @version 1.1
 * @see java.util.Scanner
 * @since JDK1.8
 */
public class UserInterface {

    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    scanner.close();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    // request functions

    /**
     * Fordert den Nutzer auf {@code Enter} oder {@code Return} zu dr&uuml;cken.
     */
    public static void requestUserPressReturn() {
        System.out.println(" -- ENTER/RETURN dr\u00FCcken --");
        scanner.nextLine();
    }

    /**
     * Fordert den Nutzer auf eine Ganzzahl einzugeben.
     * <p>
     * Beispielnutzung:
     * <p>
     * <code>
     * requestInt("Bitte Ganzzahl eingeben:");
     * </code>
     *
     * @param message anzuzeigende Nachricht mit Aufforderung
     * @return eingegebene Ganzzahl
     */
    public static int requestInt(String message) {
        Integer answer;
        do {
            answer = parseInt(askQuestion(message));
        } while (answer == null);
        return answer;
    }

    /**
     * Fordert den Nutzer auf eine Ganzzahl (&gt;= {@code minimum}) einzugeben.
     * <p>
     * Beispielnutzung:
     * <p>
     * <code>
     * requestInt("Bitte Ganzzahl &gt;= 5 eingeben:", 5);
     * </code>
     *
     * @param message anzuzeigende Nachricht mit Aufforderung
     * @param minimum Minimalwert (inklusive)
     * @return eingegebene Ganzzahl
     */
    public static int requestInt(String message, int minimum) {
        Integer answer;
        do {
            answer = parseInt(askQuestion(message), minimum);
        } while (answer == null);
        return answer;
    }

    /**
     * Fordert den Nutzer auf eine Ganzzahl (&gt;= {@code minimum} &amp; &lt;= {@code maximum}) einzugeben.
     * <p>
     * Beispielnutzung:
     * <p>
     * <code>
     * requestInt("Bitte Ganzzahl &gt;= 5 und &lt;= 10 eingeben:", 5, 10);
     * </code>
     *
     * @param message anzuzeigende Nachricht mit Aufforderung
     * @param minimum Minimalwert (inklusive)
     * @param maximum maximalwert (inklusive)
     * @return eingegebene Ganzzahl
     */
    public static int requestInt(String message, int minimum, int maximum) {
        Integer answer;
        do {
            answer = parseInt(askQuestion(message), minimum, maximum);
        } while (answer == null);
        return answer;
    }

    /**
     * Fordert den Nutzer auf eine Flie&szlig;kommazahl einzugeben.
     * <p>
     * Beispielnutzung:
     * <p>
     * <code>
     * requestDouble("Bitte Flie&szlig;kommazahl eingeben:");
     * </code>
     *
     * @param message anzuzeigende Nachricht mit Aufforderung
     * @return eingegebene Flie&szlig;kommazahl
     */
    public static double requestDouble(String message) {
        Double answer;
        do {
            answer = parseDouble(askQuestion(message));
        } while (answer == null);
        return answer;
    }

    /**
     * Fordert den Nutzer auf eine Flie&szlig;kommazahl (&gt;= {@code minimum}) einzugeben.
     * <p>
     * Beispielnutzung:
     * <p>
     * <code>
     * requestDouble("Bitte Flie&szlig;kommazahl &gt;= 5 eingeben:", 5);
     * </code>
     *
     * @param message anzuzeigende Nachricht mit Aufforderung
     * @param minimum Minimalwert (inklusive)
     * @return eingegebene Flie&szlig;kommazahl
     */
    public static double requestDouble(String message, double minimum) {
        Double answer;
        do {
            answer = parseDouble(askQuestion(message), minimum);
        } while (answer == null);
        return answer;
    }

    /**
     * Fordert den Nutzer auf eine Flie&szlig;kommazahl (&gt;= {@code minimum} &amp; &lt;= {@code maximum}) einzugeben.
     * <p>
     * Beispielnutzung:
     * <p>
     * <code>
     * requestDouble("Bitte Flie&szlig;kommazahl &gt;= 5 und &lt;= 10 eingeben:", 5, 10);
     * </code>
     *
     * @param message anzuzeigende Nachricht mit Aufforderung
     * @param minimum Minimalwert (inklusive)
     * @param maximum maximalwert (inklusive)
     * @return eingegebene Flie&szlig;kommazahl
     */
    public static double requestDouble(String message, double minimum, double maximum) {
        Double answer;
        do {
            answer = parseDouble(askQuestion(message), minimum, maximum);
        } while (answer == null);
        return answer;
    }

    /**
     * Fordert den Nutzer auf einen String einzugeben.
     * <p>
     * Beispielnutzung:
     * <p>
     * <code>
     * requestString("Bitte Spielernamen eingeben:");
     * </code>
     *
     * @param message anzuzeigende Nachricht mit Aufforderung
     * @return eingegebener String
     */
    public static String requestString(String message) {
        String answer = "";
        do {
            answer = askQuestion(message);
        } while (answer.isBlank());
        return answer;
    }

    /**
     * Fordert den Nutzer auf zwischen verschiedenen Optionen zu w&auml;hlen.
     * <p>
     * Beispielnutzung:
     * <p>
     * <code>
     * requestChoice("W&auml;hle eine Variante", "Var-1", "Var-2");
     * <p>
     * Konsoleneingabe: Var-2 resultiert in R&uuml;ckgabe: 1
     * </code>
     * <p>
     * Sonderfall:
     * <p>
     * Wenn nur eine einzige Option &uuml;bergeben wurde,
     * dann wird diese automatisch gew&aumml;hlt.
     *
     * @param message       anzuzeigende Nachricht mit Aufforderung
     * @param choiceOptions m&ouml;gliche Optionen ({@code Array} oder {@code Sequenz von Argumenten})
     * @return int - Index der gew&auml;hlten Option
     * @throws UserInterfaceException falls keine {@code choiceOptions} &uuml;bergeben wurden.
     */
    public static int requestChoice(String message, String... choiceOptions) {
        if (choiceOptions == null || choiceOptions.length == 0) {
            throw new UserInterfaceException("Keine Optionen zur Auswahl \u00FCbergeben.");
        }
        if (choiceOptions.length == 1) {
            System.out.printf(" Nur eine Option w\u00E4hlbar.\n W\u00E4hle %s.\n", choiceOptions[0]);
            return 0;
        }
        String choiceString = Arrays.toString(choiceOptions).replaceAll("[\\[\\]]", "");
        int givenChoice = -1;
        do {
            String choice = askQuestion(String.format("%s: %s", message, choiceString));
            for (int i = 0; i < choiceOptions.length; i++) {
                if (choice.equalsIgnoreCase(choiceOptions[i])) {
                    givenChoice = i;
                    break;
                }
            }
            if (givenChoice == -1) {
                System.out.println(" Die Auswahl muss eine der Optionen sein.\n");
            }
        } while (givenChoice == -1);
        return givenChoice;
    }

    /**
     * Fordert den Nutzer auf zwischen verschiedenen Optionen zu w&auml;hlen.
     * <p>
     * Beispielnutzung:
     * <p>
     * <code>
     * requestChoice("W&auml;hle eine Variante", "Var-1", "Var-2");
     * <p>
     * Konsoleneingabe: Var-2 resultiert in R&uuml;ckgabe: "Var-2"
     * </code>
     * <p>
     * Sonderfall:
     * <p>
     * Wenn nur eine einzige Option &uuml;bergeben wurde,
     * dann wird diese automatisch gew&aumml;hlt.
     *
     * @param message       anzuzeigende Nachricht mit Aufforderung
     * @param choiceOptions m&ouml;gliche Optionen ({@code Array} oder {@code Sequenz von Argumenten})
     * @return String - Name der gew&auml;hlten Option
     * @throws UserInterfaceException falls keine {@code choiceOptions} &uuml;bergeben wurden.
     */
    public static String requestChoiceName(String message, String... choiceOptions) {
        if (choiceOptions == null || choiceOptions.length == 0) {
            throw new UserInterfaceException("Keine Optionen zur Auswahl \u00FCbergeben.");
        }
        return choiceOptions[requestChoice(message, choiceOptions)];
    }

    // utility function

    private static String askQuestion(String question) {
        System.out.printf(" %s\n ", question);
        return scanner.nextLine();
    }

    // Parsing functions

    private static Integer parseInt(String string) {
        int number;
        try {
            number = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            System.out.println(" Es muss eine Ganzzahl eingegeben werden!");
            return null;
        }
        return number;
    }

    private static Integer parseInt(String string, int minimum) {
        Integer number = parseInt(string);
        if (number != null && number < minimum) {
            System.out.printf(" Die Zahl muss gr\u00F6\u00DFer gleich \"%d\" sein!\n", minimum);
            return null;
        }
        return number;
    }

    private static Integer parseInt(String string, int minimum, int maximum) {
        Integer number = parseInt(string, minimum);
        if (number != null && number > maximum) {
            System.out.printf(" Die Zahl muss kleiner gleich \"%d\" sein!\n", maximum);
            return null;
        }
        return number;
    }

    private static Double parseDouble(String string) {
        double number;
        try {
            number = Double.parseDouble(string.replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println(" Es muss eine Flie\u00DFkommazahl eingegeben werden!");
            return null;
        }
        return number;
    }

    private static Double parseDouble(String string, double minimum) {
        Double number = parseDouble(string);
        if (number != null && number < minimum) {
            System.out.printf(" Die Zahl muss gr\u00F6\u00DFer gleich \"%.2f\" sein!\n", minimum);
            return null;
        }
        return number;
    }

    private static Double parseDouble(String string, double minimum, double maximum) {
        Double number = parseDouble(string, minimum);
        if (number != null && number > maximum) {
            System.out.printf(" Die Zahl muss kleiner gleich \"%.2f\" sein!\n", maximum);
            return null;
        }
        return number;
    }

    static class UserInterfaceException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public UserInterfaceException(String message) {
            super(message);
        }

    }
}
