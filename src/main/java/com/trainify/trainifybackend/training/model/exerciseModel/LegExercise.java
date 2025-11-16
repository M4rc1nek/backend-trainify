package com.trainify.trainifybackend.training.model.exerciseModel;

import lombok.Getter;

@Getter
public enum LegExercise {
    PRZYSIAD_KLASYCZNY("Przysiad klasyczny"),
    PRZYSIAD_SUMO("Przysiad sumo"),
    PRZYSIAD_BULGARSKI("Przysiad bułgarski"),
    PRZYSIAD_JEDNONOGI_PROGRESJA("Przysiad na jednej nodze – progresja"),
    WYKROK_DO_PRZODU("Wykrok do przodu"),
    WYKROK_W_TYL("Wykrok w tył"),
    WYKROK_BOCZNY("Wykrok boczny"),
    SKOK_W_MIEJSCU("Skok w miejscu"),
    SKOK_NA_JEDNEJ_NODZE("Skok na jednej nodze"),
    GLUTE_BRIDGE("Glute Bridge"),
    GLUTE_BRIDGE_JEDNA_NOGA("Glute Bridge na jednej nodze"),
    DONKEY_KICK("Donkey Kick"),
    WZNOSY_NA_PALCE("Wznosy na palce"),
    WALL_SIT("Wall Sit"),
    SKOK_W_DAL_Z_MIEJSCA("Skok w dal z miejsca");


    private final String nazwa;

    LegExercise(String nazwa) {
        this.nazwa= nazwa;
    }

}
