package com.trainify.trainifybackend.training.model;

import lombok.Getter;

@Getter
public enum BackExercise {
    PODCIAGANIE_NACHWYTEM("Podciąganie nachwytem"),
    PODCIAGANIE_PODCHWYTEM("Podciąganie podchwytem"),
    PODCIAGANIE_NEUTRALNYM_CHWYtem("Podciąganie neutralnym chwytem"),
    PODCIAGANIE_SZEROKIM_NACHWYTEM("Podciąganie szerokim nachwytem"),
    PODCIAGANIE_WASKIM_NACHWYTEM("Podciąganie wąskim nachwytem"),
    PODCIAGANIE_LUKOWE("Podciąganie łukowe"),
    PODCIAGANIE_TYPEWRITER("Podciąganie typu typewriter"),
    WIOSLOWANIE_AUSTRALIJSKIE("Wiosłowanie australijskie"),
    WIOSLOWANIE_POZIOME("Wiosłowanie poziome"),
    WIOSLOWANIE_NA_KOLKACH("Wiosłowanie na kółkach"),
    SCAPULAR_PULL_UP("Scapular Pull-Up"),
    BIRD_DOG("Bird-Dog"),
    SUPERMAN("Superman"),
    FRONT_LEVER_TUCK_HOLD("Front Lever Tuck Hold"),
    PODCIAGANIE_COMMANDO("Podciąganie commando");

    private final String nazwa;

    BackExercise(String nazwa) {
        this.nazwa = nazwa;
    }
}
