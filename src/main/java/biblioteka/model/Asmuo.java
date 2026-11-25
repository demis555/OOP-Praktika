package biblioteka.model;

import biblioteka.util.Validatorius;

/** Abstrakti bazinė klasė: bendri žmogaus duomenys. */
public abstract class Asmuo {

    private int id;
    private String vardas;
    private String pavarde;
    private String elPastas;

    protected Asmuo(int id, String vardas, String pavarde, String elPastas) {
        this.id = id;
        setVardas(vardas);
        setPavarde(pavarde);
        setElPastas(elPastas);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVardas() {
        return vardas;
    }

    // "final", nes šie metodai kviečiami konstruktoriuje
    public final void setVardas(String vardas) {
        this.vardas = Validatorius.tikrintiNeTuscia("Vardas", vardas);
    }

    public String getPavarde() {
        return pavarde;
    }

    public final void setPavarde(String pavarde) {
        this.pavarde = Validatorius.tikrintiNeTuscia("Pavardė", pavarde);
    }

    public String getElPastas() {
        return elPastas;
    }

    public final void setElPastas(String elPastas) {
        this.elPastas = Validatorius.tikrintiElPasta(elPastas);
    }

    public String getVisasVardas() {
        return vardas + " " + pavarde;
    }
}
