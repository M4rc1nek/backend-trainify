package com.trainify.trainifybackend.training.dto;

import com.trainify.trainifybackend.training.model.exerciseModel.ExerciseCategory;

public record TrainingExerciseDTO(

        Long id,

        ExerciseCategory exerciseCategory,
        String exerciseName,
        String exerciseDisplayName,



        int amount,
        int duration

        ) {
}
