package com.trainify.trainifybackend.training.controller;


import com.trainify.trainifybackend.training.dto.TrainingDTO;
import com.trainify.trainifybackend.training.dto.TrainingStatisticsDTO;
import com.trainify.trainifybackend.training.service.TrainingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;


    @PostMapping("/training")
    public ResponseEntity<TrainingDTO> addTrainingEndpoint(@RequestBody @Valid TrainingDTO trainingDTO) {
        return ResponseEntity.ok(trainingService.addTraining(trainingDTO));
    }


    @DeleteMapping("/training/{trainingId}/{userId}")
    public ResponseEntity<Void> deleteTrainingEndpoint(@PathVariable Long trainingId, @PathVariable Long userId) {
        trainingService.deleteTraining(trainingId, userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/training/{trainingId}/{userId}")
    public ResponseEntity<TrainingDTO> updateTrainingEndpoint(@RequestBody @Valid TrainingDTO trainingDTO, @PathVariable Long trainingId, @PathVariable Long userId) {
        return ResponseEntity.ok(trainingService.updateTraining(trainingDTO, trainingId, userId));
    }

    @GetMapping("/training/{userId}")
    public ResponseEntity<TrainingStatisticsDTO> getStatisticsForUserIdEndpoint(@PathVariable Long userId) {
        return ResponseEntity.ok(trainingService.getStatisticsForUserId(userId));
    }

    @GetMapping("/training/history")
    public ResponseEntity<List<TrainingDTO>> getTrainingHistoryEndpoint() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(trainingService.getTrainingsForUserByEmail(userEmail));
    }


}
