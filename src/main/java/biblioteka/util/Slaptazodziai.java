package biblioteka.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HexFormat;

/**
 * Slaptažodžių saugojimas: generuojama druska ir skaičiuojamas hash(druska + slaptažodis).
 */
public final class Slaptazodziai {

    private static final SecureRandom RANDOM = new SecureRandom();

    private Slaptazodziai() {
    }

    public static String sukurtiDruska() {
        byte[] baitai = new byte[16];
        RANDOM.nextBytes(baitai);
        return Base64.getEncoder().encodeToString(baitai);
    }

    public static String hash(String druska, String slaptazodis) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] h = md.digest((druska + slaptazodis).getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(h);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 nepalaikomas", e);
        }
    }
}
