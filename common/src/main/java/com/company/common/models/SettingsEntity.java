package com.company.common.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class SettingsEntity {

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
}
