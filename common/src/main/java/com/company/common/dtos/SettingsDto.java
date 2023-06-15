package com.company.common.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDto {

    private int id;

    @Min(3)
    @Max(100)
    private int squareSize;

    //ToDo: add max restriction (dynamically)
    @Min(3)
    private int linesCountForWin;

    @NotNull(message = "cross player id must not be empty")
    private int crossPlayerId;

    @NotNull(message = "cross player id must not be empty")
    private int zeroPlayerId;

    @NotNull(message = "move time limit must not be empty")
    @Positive
    private int moveTimeLimit;

    @NotNull(message = "game id must not be empty")
    private int gameId;
}
