package com.trainify.trainifybackend.dailywellness.controller;

import com.trainify.trainifybackend.dailywellness.dto.DailyWellnessDTO;
import com.trainify.trainifybackend.dailywellness.service.DailyWellnessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DailyWellnessController {

    private final DailyWellnessService dailyWellnessService;

    @PostMapping("/daily-wellness/submit")
    public ResponseEntity<DailyWellnessDTO> submitCheck(@RequestBody @Valid DailyWellnessDTO dto) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(dailyWellnessService.submitCheck(dto, userEmail));
    }

    @GetMapping("/daily-wellness/history")
    public ResponseEntity<List<DailyWellnessDTO>> getDailyWellnessHistory() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(dailyWellnessService.getDailyWellnessHistory(userEmail));
    }

    @DeleteMapping("/daily-wellness/{userId}")
    public ResponseEntity<Void> deleteDailyWellness(@PathVariable Long userId, @RequestParam LocalDate date) {
        dailyWellnessService.deleteDailyWellness(userId, date);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/daily-wellness/{userId}")
    public ResponseEntity<DailyWellnessDTO> updateDailyWellness(@RequestBody @Valid DailyWellnessDTO dto, @PathVariable Long userId, @RequestParam LocalDate date) {
        return ResponseEntity.ok(dailyWellnessService.updateDailyWellness(dto, userId, date));
    }

}
