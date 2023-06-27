package com.company.common.models;

import lombok.Data;

import jakarta.persistence.*;
import java.util.UUID;

@Entity(name = "settings")
@Data
public class SettingsEntity {

    @Id
    private UUID id;

    @Column(name = "square_size")
    private int squareSize;

    @Column(name = "lines_count_for_win")
    private int linesCountForWin;

    @Column(name = "x_player_id")
    private int xPlayerId;

    @Column(name = "o_player_id")
    private int oPlayerId;

    @Column(name = "move_time_limit")
    private int moveTimeLimit;

    @OneToOne(mappedBy = "settings", fetch = FetchType.LAZY)
    private GameEntity game;
}
