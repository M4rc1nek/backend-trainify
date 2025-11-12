package com.trainify.trainifybackend.training.repository;

import com.trainify.trainifybackend.training.model.Training;
import com.trainify.trainifybackend.training.model.TrainingExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {


    Optional<Training> findTrainingByIdAndUserAssigned_Id(Long trainingId, Long userId); // metoda do pobierania treningu po ID treningu i ID użytkownika

    List<Training> findAllByUserAssigned_Id(Long userId); // Pobieranie wszystkich treningów danego użytkownika

    List<TrainingExercise> findAllExerciseByUserAssigned_Id(Long userId); // Pobieranie wszystkich ćwiczeń danego użytkownika


}
