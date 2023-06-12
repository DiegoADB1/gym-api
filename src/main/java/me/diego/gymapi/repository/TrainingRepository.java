package me.diego.gymapi.repository;

import me.diego.gymapi.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
