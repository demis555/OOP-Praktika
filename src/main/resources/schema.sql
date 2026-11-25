PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS naudotojas (
    id                  INTEGER PRIMARY KEY AUTOINCREMENT,
    vardas              TEXT NOT NULL,
    pavarde             TEXT NOT NULL,
    el_pastas           TEXT NOT NULL UNIQUE,
    prisijungimo_vardas TEXT NOT NULL UNIQUE,
    slaptazodzio_hash   TEXT NOT NULL,
    druska              TEXT NOT NULL,
    role                TEXT NOT NULL
        CHECK (role IN ('ADMIN', 'BIBLIOTEKININKAS', 'SKAITYTOJAS'))
);

CREATE TABLE IF NOT EXISTS leidinys (
    id            INTEGER PRIMARY KEY AUTOINCREMENT,
    tipas         TEXT NOT NULL CHECK (tipas IN ('KNYGA', 'ZURNALAS')),
    pavadinimas   TEXT NOT NULL,
    metai         INTEGER NOT NULL CHECK (metai > 0),
    egzemplioriai INTEGER NOT NULL DEFAULT 1 CHECK (egzemplioriai >= 0),
    autorius      TEXT,
    isbn          TEXT UNIQUE,
    zanras        TEXT,
    numeris       INTEGER
);

CREATE TABLE IF NOT EXISTS paskola (
    id             INTEGER PRIMARY KEY AUTOINCREMENT,
    leidinio_id    INTEGER NOT NULL REFERENCES leidinys(id),
    naudotojo_id   INTEGER NOT NULL REFERENCES naudotojas(id),
    paemimo_data   TEXT NOT NULL,
    terminas       TEXT NOT NULL,
    grazinimo_data TEXT
);
