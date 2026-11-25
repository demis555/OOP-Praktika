package biblioteka.model;

import biblioteka.util.Validatorius;

/** Abstrakti klasė: bendri bibliotekos leidinio (knygos, žurnalo) duomenys. */
public abstract class Leidinys {

    private int id;
    private String pavadinimas;
    private int metai;
    private int egzemplioriai;

    protected Leidinys(int id, String pavadinimas, int metai, int egzemplioriai) {
        this.id = id;
        setPavadinimas(pavadinimas);
        setMetai(metai);
        setEgzemplioriai(egzemplioriai);
    }

    /** Kiek dienų leidinį galima skolintis. Kiekvienas leidinio tipas atsako savaip. */
    public abstract int getSkolinimoDienos();

    /** Tipas, kuris saugomas DB stulpelyje "tipas". */
    public abstract String getTipas();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public final void setPavadinimas(String pavadinimas) {
        this.pavadinimas = Validatorius.tikrintiNeTuscia("Pavadinimas", pavadinimas);
    }

    public int getMetai() {
        return metai;
    }

    public final void setMetai(int metai) {
        this.metai = Validatorius.tikrintiMetus(metai);
    }

    public int getEgzemplioriai() {
        return egzemplioriai;
    }

    public final void setEgzemplioriai(int egzemplioriai) {
        this.egzemplioriai = Validatorius.tikrintiNeNeigiama("Egzemplioriai", egzemplioriai);
    }

    public boolean arLaisvas() {
        return egzemplioriai > 0;
    }

    @Override
    public String toString() {
        return pavadinimas + " (" + metai + ")";
    }
}
