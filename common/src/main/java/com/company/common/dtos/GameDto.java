package com.company.common.dtos;

import com.company.common.models.enums.GameStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    private UUID id;

    @NotNull(message = "settings id must not be empty")
    private int settingsId;

    @NotNull(message = "room id must not be empty")
    private int roomId;

    @NotNull(message = "Game status must not be null")
    private GameStatus status;
}