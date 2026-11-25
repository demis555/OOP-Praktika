package biblioteka.model;

import java.util.EnumSet;
import java.util.Set;

/** Naudotojo rolė. Kiekviena rolė žino, kokius veiksmus jai leidžiama atlikti. */
public enum Role {

    ADMIN(EnumSet.allOf(Veiksmas.class)),

    BIBLIOTEKININKAS(EnumSet.of(
            Veiksmas.PERZIURETI_KNYGAS,
            Veiksmas.SKOLINTI,
            Veiksmas.PRIIMTI_GRAZINIMA,
            Veiksmas.PERZIURETI_VELUOJANCIAS,
            Veiksmas.VALDYTI_KNYGAS,
            Veiksmas.VALDYTI_SKAITYTOJUS)),

    SKAITYTOJAS(EnumSet.of(
            Veiksmas.PERZIURETI_KNYGAS,
            Veiksmas.PERZIURETI_SAVO_PASKOLAS));

    private final Set<Veiksmas> teises;

    Role(Set<Veiksmas> teises) {
        this.teises = teises;
    }

    public boolean turiTeise(Veiksmas veiksmas) {
        return teises.contains(veiksmas);
    }
}
