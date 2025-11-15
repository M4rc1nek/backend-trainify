package com.trainify.trainifybackend.training.model;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@Table(name = "trainingExercise")
@AllArgsConstructor
@NoArgsConstructor

public class TrainingExercise {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private ExerciseCategory exerciseCategory; // Kategoria ćwiczenia (np. Ramiona)
    private String exerciseName;  // DIPY_NA_PORECZACH
    private String exerciseDisplayName; // Dipy na poręczach


    private int amount; // liczba powtórzeń

    private int duration; // czas w minutach cwiczenia





    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training trainingAssigned;


}
