package com.company.common.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDto {

    private UUID id;

    @Min(3)
    @Max(100)
    private int squareSize;

    @Min(3)
    @Max(100)
    private int linesCountForWin;

    @NotNull(message = "x player id must not be empty")
    private int xPlayerId;

    @NotNull(message = "o player id must not be empty")
    private int oPlayerId;

    @NotNull(message = "move time limit must not be empty")
    @Positive
    private int moveTimeLimit;

    @NotNull(message = "game id must not be empty")
    private int gameId;
}
