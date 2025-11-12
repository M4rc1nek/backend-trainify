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

    private ExerciseCategory exerciseCategory; // kategoria ćwiczenia
    private String note; // opcjonalna notatka

    private int amount; // liczba powtórzeń

    private int duration; // czas w minutach cwiczenia

    private double intensityScore; // Wynik intensywnosci treningu 0 - 100
    private String intensityScoreMessage; // Komunikat jak intensywny trening b


    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training trainingAssigned;


}
