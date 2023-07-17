package com.company.common.models;

import com.company.common.models.enums.GameStatus;
import com.company.common.models.enums.GameVisibility;
import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity winner;

    @OneToMany(mappedBy = "game")
    private List<MoveEntity> moves;

    private LocalDateTime creationDate;
    private String link;
}
