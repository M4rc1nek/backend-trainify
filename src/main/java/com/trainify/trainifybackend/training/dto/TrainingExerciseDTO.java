package com.trainify.trainifybackend.training.dto;

import com.trainify.trainifybackend.training.model.ExerciseCategory;

public record TrainingExerciseDTO(

        Long id,

        ExerciseCategory exerciseCategory,

        int amount,
        int duration

        ) {
}
