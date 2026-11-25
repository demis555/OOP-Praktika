package biblioteka.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** Vieno leidinio paskolinimas vienam naudotojui. */
public class Paskola {

    private int id;
    private final Leidinys leidinys;
    private final Naudotojas naudotojas;
    private final LocalDate paemimoData;
    private final LocalDate terminas;
    private LocalDate grazinimoData; // null, kol leidinys negrąžintas

    /** Nauja paskola: paėmimo data šiandien, terminas priklauso nuo leidinio tipo (polimorfizmas). */
    public Paskola(Leidinys leidinys, Naudotojas naudotojas) {
        this.leidinys = leidinys;
        this.naudotojas = naudotojas;
        this.paemimoData = LocalDate.now();
        this.terminas = paemimoData.plusDays(leidinys.getSkolinimoDienos());
    }

    /** Konstruktorius skaitant iš duomenų bazės. */
    public Paskola(int id, Leidinys leidinys, Naudotojas naudotojas,
                   LocalDate paemimoData, LocalDate terminas, LocalDate grazinimoData) {
        this.id = id;
        this.leidinys = leidinys;
        this.naudotojas = naudotojas;
        this.paemimoData = paemimoData;
        this.terminas = terminas;
        this.grazinimoData = grazinimoData;
    }

    public boolean arGrazinta() {
        return grazinimoData != null;
    }

    public void grazinti() {
        if (arGrazinta()) {
            throw new IllegalStateException("Leidinys jau grąžintas.");
        }
        this.grazinimoData = LocalDate.now();
    }

    /** Vėluoja, jei dar negrąžinta ir terminas jau praėjo. */
    public boolean arVeluoja() {
        return !arGrazinta() && LocalDate.now().isAfter(terminas);
    }

    public long veluojaDienu() {
        return arVeluoja() ? ChronoUnit.DAYS.between(terminas, LocalDate.now()) : 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Leidinys getLeidinys() {
        return leidinys;
    }

    public Naudotojas getNaudotojas() {
        return naudotojas;
    }

    public LocalDate getPaemimoData() {
        return paemimoData;
    }

    public LocalDate getTerminas() {
        return terminas;
    }

    public LocalDate getGrazinimoData() {
        return grazinimoData;
    }

    @Override
    public String toString() {
        return leidinys + " -> " + naudotojas.getVisasVardas() + ", terminas " + terminas
                + (arGrazinta() ? ", grąžinta " + grazinimoData : "");
    }
}
