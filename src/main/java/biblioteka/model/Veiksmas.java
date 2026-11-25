package biblioteka.model;

/** Visi veiksmai, kuriuos sistemoje gali atlikti naudotojai (teisės tikrinamos pagal juos). */
public enum Veiksmas {
    PERZIURETI_KNYGAS,
    PERZIURETI_SAVO_PASKOLAS,
    SKOLINTI,
    PRIIMTI_GRAZINIMA,
    PERZIURETI_VELUOJANCIAS,
    VALDYTI_KNYGAS,        // pridėti ir redaguoti
    SALINTI_KNYGAS,
    VALDYTI_SKAITYTOJUS,
    VALDYTI_PASKYRAS       // kurti paskyras, keisti roles
}
