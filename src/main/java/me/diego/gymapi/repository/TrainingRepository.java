package me.diego.gymapi.repository;

import me.diego.gymapi.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findAllByUserId(Long id);
}
