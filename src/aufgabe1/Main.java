package aufgabe1;

public class Main {

    public static void main(String[] args) {
        String[] namen = {"Heisenberg", "Jessie"/*"Mike", "Jimmy", "Gus", "Tuco"*/};

        Abseitsspiel abseitsspiel = new Abseitsspiel(namen);

        abseitsspiel.starteSpiel();
    }
}
