package com.trainify.trainifybackend.training.model.exerciseModel;

import lombok.Getter;

@Getter
public enum ArmExercise {
    DIPY_NA_PORECZACH("Dipy na poręczach"),
    DIPY_NA_KOLKACH("Dipy na kółkach"),
    DIPY_IZOMETRYCZNE("Dipy izometryczne"),
    POMPKI_DIAMENTOWE("Pompki diamentowe"),
    POMPKI_WASKIE("Pompki wąskie"),
    L_SIT_DO_DIPOW("L-sit do dipów"),
    PODCIAGANIE_PODCHWYTEM("Podciąganie podchwytem"),
    PODCIAGANIE_NEUTRALNYM_CHWYtem("Podciąganie neutralnym chwytem"),
    PODCIAGANIE_Z_SUPINACJA("Podciąganie z supinacją"),
    NEGATYWNE_PODCIAGANIE_PODCHWYTEM("Negatywne podciąganie podchwytem"),
    POMPKI_ARCHER("Pompki archer"),
    SLOW_DIP("Slow Dip"),
    POMPKI_WASKIE_WOLNE("Pompki wąskie wolne"),
    POMPKI_PLANCHE_LEAN_RAMIONA("Pompki typu planche lean"),
    PULL_UP_EKSPL("Pull-Up Explosive");

    private final String nazwa;

    ArmExercise(String nazwa) {
        this.nazwa = nazwa;
    }
}
