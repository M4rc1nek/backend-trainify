package com.trainify.trainifybackend.training.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record TrainingDTO(

        Long id,
        Long userId,

        String note,

        @PastOrPresent LocalDate date,
        @JsonInclude(JsonInclude.Include.NON_NULL) LocalDateTime createdAt,

        List<TrainingExerciseDTO> exercises


) {
}
