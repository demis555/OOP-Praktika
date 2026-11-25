package biblioteka.model;

import biblioteka.util.Validatorius;

public class Knyga extends Leidinys {

    private String autorius;
    private String isbn;
    private String zanras;

    /** Konstruktorius skaitant iš DB (su id). */
    public Knyga(int id, String pavadinimas, int metai, int egzemplioriai,
                 String autorius, String isbn, String zanras) {
        super(id, pavadinimas, metai, egzemplioriai);
        setAutorius(autorius);
        setIsbn(isbn);
        this.zanras = zanras;
    }

    /** Perkrautas konstruktorius naujai knygai (id priskirs duomenų bazė). */
    public Knyga(String pavadinimas, int metai, int egzemplioriai,
                 String autorius, String isbn, String zanras) {
        this(0, pavadinimas, metai, egzemplioriai, autorius, isbn, zanras);
    }

    @Override
    public int getSkolinimoDienos() {
        return 30;
    }

    @Override
    public String getTipas() {
        return "KNYGA";
    }

    public String getAutorius() {
        return autorius;
    }

    public final void setAutorius(String autorius) {
        this.autorius = Validatorius.tikrintiNeTuscia("Autorius", autorius);
    }

    public String getIsbn() {
        return isbn;
    }

    public final void setIsbn(String isbn) {
        this.isbn = Validatorius.tikrintiIsbn(isbn);
    }

    public String getZanras() {
        return zanras;
    }

    public void setZanras(String zanras) {
        this.zanras = zanras;
    }

    @Override
    public String toString() {
        return autorius + ": " + super.toString();
    }
}
