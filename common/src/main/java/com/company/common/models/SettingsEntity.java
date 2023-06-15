package com.company.common.models;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "settings")
@Data
public class SettingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "square_size")
    private int squareSize;

    @Column(name = "lines_count_for_win")
    private int linesCountForWin;

    @Column(name = "cross_player_id")
    private int crossPlayerId;

    @Column(name = "zero_player_id")
    private int zeroPlayerId;

    @Column(name = "move_time_limit")
    private int moveTimeLimit;

    @OneToOne(mappedBy = "settings", fetch = FetchType.LAZY)
    private GameEntity game;
}
