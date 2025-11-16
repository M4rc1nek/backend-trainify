package com.trainify.trainifybackend.dailywellness.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record DailyWellnessDTO(

        Long id,

        @PastOrPresent LocalDate date,


        @Min(0) @Max(24) double hoursSlept,
        @Min(1) @Max(10) int energyLevel,
        @Min(1) @Max(10) int musclePain,
        @Min(1) @Max(10) int mood,
        @Min(1) @Max(10) int motivation,

        int readinessScore,
        String readinessLevel,
        String recommendation
) {
}
