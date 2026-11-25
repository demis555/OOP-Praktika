package biblioteka.model;

import biblioteka.util.Validatorius;

public class Zurnalas extends Leidinys {

    private int numeris;

    public Zurnalas(int id, String pavadinimas, int metai, int egzemplioriai, int numeris) {
        super(id, pavadinimas, metai, egzemplioriai);
        setNumeris(numeris);
    }

    public Zurnalas(String pavadinimas, int metai, int egzemplioriai, int numeris) {
        this(0, pavadinimas, metai, egzemplioriai, numeris);
    }

    @Override
    public int getSkolinimoDienos() {
        return 7;
    }

    @Override
    public String getTipas() {
        return "ZURNALAS";
    }

    public int getNumeris() {
        return numeris;
    }

    public final void setNumeris(int numeris) {
        this.numeris = Validatorius.tikrintiTeigiama("Numeris", numeris);
    }

    @Override
    public String toString() {
        return super.toString() + " Nr. " + numeris;
    }
}
