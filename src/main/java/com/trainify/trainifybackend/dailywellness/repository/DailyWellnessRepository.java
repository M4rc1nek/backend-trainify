package com.trainify.trainifybackend.dailywellness.repository;

import com.trainify.trainifybackend.dailywellness.model.DailyWellness;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyWellnessRepository extends JpaRepository<DailyWellness, Long> {

    Optional<DailyWellness> findByUserAssigned_IdAndDate(Long userId, LocalDate date);

    List<DailyWellness> findAllByUserAssigned_IdOrderByDateDesc(Long userId);


}
