package me.diego.gymapi.controller;

import lombok.RequiredArgsConstructor;
import me.diego.gymapi.model.Training;
import me.diego.gymapi.repository.TrainingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/training")
public class TrainingController {

    private final TrainingRepository trainingRepository;

    @GetMapping
    public ResponseEntity<List<Training>> getTrainings() {
        return ResponseEntity.ok(trainingRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Training> createTraining(@RequestBody Training training) {
        Training saveTraining = new Training(null, training.getName(), training.getDescription(), LocalDateTime.now());

        return ResponseEntity.ok(trainingRepository.save(saveTraining));

    }

    @DeleteMapping("/{id}")
    public void deleteTraining(@PathVariable Long id) {
        trainingRepository.deleteById(id);
    }
}
