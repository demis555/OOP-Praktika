package biblioteka.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

/**
 * Atsakinga už ryšį su SQLite duomenų baze ir lentelių sukūrimą.
 */
public final class Db {

    private static final String URL = "jdbc:sqlite:biblioteka.db";

    private Db() {
    }

    /** Atidaro naują ryšį. Išoriniai raktai SQLite turi būti įjungiami kiekvieną kartą. */
    public static Connection connect() throws SQLException {
        Connection c = DriverManager.getConnection(URL);
        try (Statement st = c.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON");
        }
        return c;
    }

    /** Sukuria lenteles, jei jų dar nėra (skriptas schema.sql yra resources aplanke). */
    public static void init() throws SQLException, IOException {
        String sql;
        try (InputStream in = Db.class.getResourceAsStream("/schema.sql")) {
            if (in == null) {
                throw new IOException("Nerastas schema.sql resources aplanke");
            }
            try (BufferedReader r = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                sql = r.lines().collect(Collectors.joining("\n"));
            }
        }
        try (Connection c = connect(); Statement st = c.createStatement()) {
            for (String part : sql.split(";")) {
                if (!part.isBlank()) {
                    st.execute(part);
                }
            }
        }
    }
}
