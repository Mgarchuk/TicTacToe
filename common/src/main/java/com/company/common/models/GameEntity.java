package com.company.common.models;

import com.company.common.models.enums.GameStatus;
import lombok.Data;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "game")
@Data
public class GameEntity {

    @Id
    private UUID id;

    private String link;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settings_id", referencedColumnName = "id")
    private SettingsEntity settings;

    @Column(name = "game_status")
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @OneToMany(mappedBy = "game")
    private List<MoveEntity> moves;
}
