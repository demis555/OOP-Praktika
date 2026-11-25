package biblioteka;

import biblioteka.db.Db;
import biblioteka.exception.NetinkamaIvestisException;
import biblioteka.model.*;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            Db.init();
            System.out.println("Duomenų bazė paruošta.");
        } catch (Exception e) {
            System.err.println("Klaida: " + e.getMessage());
        }

        Naudotojas ona = Naudotojas.naujas(
                "Ona", "Onaitė", "ona@pastas.lt", "ona", "labas123", Role.SKAITYTOJAS);

        // Polimorfizmas: sąrašas turi bendrą tipą Leidinys, bet objektai skirtingi
        List<Leidinys> leidiniai = List.of(
                new Knyga("Rojus", 2019, 3, "Jonas Petraitis", "978-609-01-3456-7", "Romanas"),
                new Zurnalas("Mokslas ir gyvenimas", 2024, 5, 11));

        for (Leidinys l : leidiniai) {
            System.out.println(l + " - skolinimo terminas: " + l.getSkolinimoDienos() + " d.");
            Paskola p = new Paskola(l, ona);
            System.out.println("  " + p);
        }

        // Vėluojanti paskola (sukurta rankiniu būdu su praeities data)
        Paskola sena = new Paskola(1, leidiniai.get(0), ona,
                LocalDate.now().minusDays(40), LocalDate.now().minusDays(10), null);
        System.out.println("Ar vėluoja: " + sena.arVeluoja() + ", dienų: " + sena.veluojaDienu());
        sena.grazinti();
        System.out.println("Po grąžinimo vėluoja: " + sena.arVeluoja());

        try {
            new Knyga("Blogas ISBN", 2020, 1, "Autorius", "123", "Kita");
        } catch (NetinkamaIvestisException e) {
            System.out.println("Sugauta klaida: " + e.getMessage());
        }
    }
}
