package com.trainify.trainifybackend.training.model.exerciseModel;

import lombok.Getter;

@Getter
public enum AbsExercise {
    PLANK("Plank"),
    PLANK_BOCZNY("Plank boczny"),
    HOLLOW_BODY_HOLD("Hollow Body Hold"),
    HOLLOW_BODY_ROCK("Hollow Body Rock"),
    L_SIT("L-sit"),
    UNOSZENIE_KOLAN_W_ZWISIE("Unoszenie kolan w zwisie"),
    UNOSZENIE_NOG_W_ZWISIE("Unoszenie nóg w zwisie"),
    TOES_TO_BAR("Toes to Bar"),
    DRAGON_FLAG("Dragon Flag"),
    V_UP("V-Up"),
    RUSKIE_SKRETY("Rosyjskie skręty"),
    MOUNTAIN_CLIMBER("Mountain Climber"),
    PLANK_KOLANO_DO_LOKCI("Plank z kolanem do łokcia"),
    WINDSHIELD_WIPERS("Windshield Wipers"),
    ROLLOUT_BZUCH("Rollout brzucha");

    private final String nazwa;

    AbsExercise(String nazwa) {
        this.nazwa = nazwa;
    }
}
