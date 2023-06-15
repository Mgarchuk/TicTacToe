package com.company.common.models;

import com.company.common.models.enums.GameStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "game")
@Data
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settings_id", referencedColumnName = "id")
    private SettingsEntity settings;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private RoomEntity room;

    @Column(name = "game_status")
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @OneToMany(mappedBy = "game")
    private List<MoveEntity> moves;
}
