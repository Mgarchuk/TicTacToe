package com.company.common.models;

import com.company.common.models.enums.GameStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity(name = "game")
@Data
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String link;

    @Embedded
    private SettingsEntity settings;

    @Column(name = "game_status")
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @OneToMany(mappedBy = "game")
    private List<MoveEntity> moves;
}
