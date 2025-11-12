package com.trainify.trainifybackend.training.model;

import lombok.Getter;

@Getter
public enum ShoulderExercise {
    POMPKI_PIKE("Pompki pike"),
    POMPKI_PIKE_PODWYZSZENIE("Pompki pike na podwyższeniu"),
    POMPKI_DIVE_BOMBER("Pompki dive bomber"),
    POMPKI_HINDU("Pompki hindu"),
    WALL_HANDSTAND_HOLD("Wall Handstand Hold"),
    HANDSTAND_SHRUG("Handstand Shrug"),
    PLANCHE_LEAN("Planche Lean"),
    POMPKI_PLANCHE_LEAN("Pompki planche lean"),
    TUCK_FRONT_LEVER_HOLD("Tuck Front Lever Hold"),
    PLANK_DOTK_BARKU("Plank z dotknięciem barku"),
    PLANK_DO_POZYCJI_DOG("Plank do pozycji psa z głową w dół"),
    RING_SUPPORT_HOLD("Ring Support Hold"),
    WZNOSY_BARKOW_STANIE_REKACH("Wznosy barków w staniu na rękach"),
    L_SIT_DO_TUCK_PLANCHE("L-sit do tuck planche"),
    POMPKI_EKSPLODUJACE_PIKE("Pompki eksplodujące w pike");

    private final String nazwa;

    ShoulderExercise(String nazwa) {
        this.nazwa = nazwa;
    }
}
