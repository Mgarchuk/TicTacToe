package com.company.common.models;

import com.company.common.models.enums.GameStatus;
import com.company.common.models.enums.GameVisibility;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "game")
@Data
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private SettingsEntity settings;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @Enumerated(EnumType.STRING)
    private GameVisibility visibility;

    private UUID winnerId;

    @ToString.Exclude
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<MoveEntity> moves;

    private LocalDateTime creationDate;
}
