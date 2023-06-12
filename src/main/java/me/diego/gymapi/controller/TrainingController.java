package me.diego.gymapi.controller;

import lombok.RequiredArgsConstructor;
import me.diego.gymapi.dto.TrainingDto;
import me.diego.gymapi.model.Training;
import me.diego.gymapi.model.UserModel;
import me.diego.gymapi.repository.TrainingRepository;
import me.diego.gymapi.repository.UserRepository;
import me.diego.gymapi.service.AuthService;
import me.diego.gymapi.service.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/training")
public class TrainingController {

    private final TrainingRepository trainingRepository;
    private final UserDetailsServiceImpl userDetailsService;

    @GetMapping
    public ResponseEntity<List<TrainingDto>> getTrainings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userDetailsService.loadUserByUsername(authentication.getName());

        List<TrainingDto> trainingDtoList = trainingRepository.findAllByUserId(user.getId()).stream().map(Training::toDto).toList();

        return ResponseEntity.ok(trainingDtoList);
    }

    @PostMapping
    public ResponseEntity<TrainingDto> createTraining(@RequestBody Training training) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userDetailsService.loadUserByUsername(authentication.getName());

        Training saveTraining = new Training(
                null,
                training.getName(),
                training.getDescription(),
                LocalDateTime.now(),
                user);

        return ResponseEntity.ok(trainingRepository.save(saveTraining).toDto());

    }

    @DeleteMapping("/{id}")
    public void deleteTraining(@PathVariable Long id) {
        trainingRepository.deleteById(id);
    }
}
