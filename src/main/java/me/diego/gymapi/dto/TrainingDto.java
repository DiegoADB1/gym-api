package me.diego.gymapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.diego.gymapi.model.Training;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime time;
}
