package me.diego.gymapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.diego.gymapi.dto.TrainingDto;

import java.time.LocalDateTime;

@Entity(name = "tb_training")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id")
    private Long id;
    private String name;
    private String description;
    private LocalDateTime time;
    @ManyToOne
    private UserModel user;

    public TrainingDto toDto() {
        return TrainingDto.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .time(this.time)
                .build();
    }
}
