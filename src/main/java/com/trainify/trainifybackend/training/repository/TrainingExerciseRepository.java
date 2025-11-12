package com.trainify.trainifybackend.training.repository;

import com.trainify.trainifybackend.training.model.TrainingExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingExerciseRepository extends JpaRepository<TrainingExercise, Long> {

    List<TrainingExercise> findAllByTrainingAssignedUserAssignedId(Long userId);

}
