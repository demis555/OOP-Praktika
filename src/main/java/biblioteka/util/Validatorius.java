package biblioteka.util;

import biblioteka.exception.NetinkamaIvestisException;

import java.util.regex.Pattern;

/**
 * Statiniai metodai įvesties tikrinimui. Jei duomenys neteisingi, meta NetinkamaIvestisException.
 */
public final class Validatorius {

    private static final Pattern EL_PASTAS = Pattern.compile("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");

    private Validatorius() {
    }

    /** Patikrina, kad tekstas nebūtų tuščias, ir grąžina jį be tarpų pradžioje ir gale. */
    public static String tikrintiNeTuscia(String laukas, String reiksme) {
        if (reiksme == null || reiksme.isBlank()) {
            throw new NetinkamaIvestisException("Laukas „" + laukas + "“ negali būti tuščias.");
        }
        return reiksme.trim();
    }

    public static String tikrintiElPasta(String reiksme) {
        String s = tikrintiNeTuscia("El. paštas", reiksme);
        if (!EL_PASTAS.matcher(s).matches()) {
            throw new NetinkamaIvestisException("Neteisingas el. pašto formatas: " + s);
        }
        return s;
    }

    public static String tikrintiSlaptazodi(String reiksme) {
        if (reiksme == null || reiksme.length() < 6) {
            throw new NetinkamaIvestisException("Slaptažodis turi būti bent 6 simbolių.");
        }
        return reiksme;
    }

    /** ISBN gali turėti brūkšnelius; po jų pašalinimo turi likti 10 arba 13 simbolių. */
    public static String tikrintiIsbn(String reiksme) {
        String s = tikrintiNeTuscia("ISBN", reiksme).replace("-", "");
        if (!s.matches("\\d{13}|\\d{9}[\\dXx]")) {
            throw new NetinkamaIvestisException("Neteisingas ISBN: " + reiksme);
        }
        return s;
    }

    public static int tikrintiTeigiama(String laukas, int reiksme) {
        if (reiksme <= 0) {
            throw new NetinkamaIvestisException("Laukas „" + laukas + "“ turi būti teigiamas skaičius.");
        }
        return reiksme;
    }

    public static int tikrintiNeNeigiama(String laukas, int reiksme) {
        if (reiksme < 0) {
            throw new NetinkamaIvestisException("Laukas „" + laukas + "“ negali būti neigiamas.");
        }
        return reiksme;
    }

    public static int tikrintiMetus(int metai) {
        int dabar = java.time.Year.now().getValue();
        if (metai < 1400 || metai > dabar) {
            throw new NetinkamaIvestisException("Neteisingi leidimo metai: " + metai);
        }
        return metai;
    }
}
