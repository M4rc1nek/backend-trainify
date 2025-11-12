package com.trainify.trainifybackend.training.model;

import lombok.Getter;

@Getter
public enum ChestExercise {
    POMPKI_KLASYCZNE("Pompki klasyczne"),
    POMPKI_SKOSNE("Pompki skośne"),
    POMPKI_SZEROKIE("Pompki szerokie"),
    DIPY_NA_PORECZACH("Dipy na poręczach"),
    DIPY_NA_KOLKACH("Dipy na kółkach"),
    POMPKI_DIAMENTOWE("Pompki diamentowe"),
    POMPKI_ARCHER("Pompki archer"),
    POMPKI_Z_WYSUNIECIEM_RAK("Pompki z wysunięciem rąk"),
    POMPKI_PLANCHE_LEAN("Pompki planche lean"),
    POMPKI_TYPEWRITER("Pompki typu typewriter"),
    POMPKI_NISKI_SUPPORT("Pompki na niskim podpórze"),
    POMPKI_EKSPLODUJACE("Pompki eksplodujące"),
    POMPKI_WOLNE("Pompki wolne"),
    POMPKI_SZEROKIE_KOLKA("Pompki na szerokich kółkach"),
    DIPY_IZOMETRYCZNE("Dipy izometryczne");  // średnik po ostatniej wartości

    private final String nazwa;  // pole do przechowywania czytelnej nazwy

    // prywatny konstruktor enum
    ChestExercise(String nazwa) {
        this.nazwa = nazwa;
    }

}
