package biblioteka.model;

import biblioteka.util.Slaptazodziai;
import biblioteka.util.Validatorius;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

/** Sistemos naudotojas: asmuo su prisijungimo duomenimis ir role. */
public class Naudotojas extends Asmuo {

    private String prisijungimoVardas;
    private final String slaptazodzioHash;
    private final String druska;
    private Role role;

    /** Konstruktorius naudojamas skaitant įrašą iš duomenų bazės (hash ir druska jau paruošti). */
    public Naudotojas(int id, String vardas, String pavarde, String elPastas,
                      String prisijungimoVardas, String slaptazodzioHash, String druska, Role role) {
        super(id, vardas, pavarde, elPastas);
        this.prisijungimoVardas = Validatorius.tikrintiNeTuscia("Prisijungimo vardas", prisijungimoVardas);
        this.slaptazodzioHash = slaptazodzioHash;
        this.druska = druska;
        this.role = role;
    }

    /** Sukuria naują naudotoją iš paprasto slaptažodžio: sugeneruoja druską ir suskaičiuoja hash. */
    public static Naudotojas naujas(String vardas, String pavarde, String elPastas,
                                    String prisijungimoVardas, String slaptazodis, Role role) {
        Validatorius.tikrintiSlaptazodi(slaptazodis);
        String druska = Slaptazodziai.sukurtiDruska();
        String hash = Slaptazodziai.hash(druska, slaptazodis);
        return new Naudotojas(0, vardas, pavarde, elPastas, prisijungimoVardas, hash, druska, role);
    }

    public boolean patikrintiSlaptazodi(String slaptazodis) {
        if (slaptazodis == null) {
            return false;
        }
        String bandomasHash = Slaptazodziai.hash(druska, slaptazodis);
        // palyginimas pastoviu laiku
        return MessageDigest.isEqual(
                bandomasHash.getBytes(StandardCharsets.UTF_8),
                slaptazodzioHash.getBytes(StandardCharsets.UTF_8));
    }

    public boolean turiTeise(Veiksmas veiksmas) {
        return role.turiTeise(veiksmas);
    }

    public String getPrisijungimoVardas() {
        return prisijungimoVardas;
    }

    public String getSlaptazodzioHash() {
        return slaptazodzioHash;
    }

    public String getDruska() {
        return druska;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return getVisasVardas() + " (" + prisijungimoVardas + ", " + role + ")";
    }
}
