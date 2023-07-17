package com.company.common.dtos;

import com.company.common.models.enums.GameStatus;
import com.company.common.models.enums.GameVisibility;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    private String id;

    private String link;

    private LocalDateTime creationDate;

    @NotNull(message = "settings dto must not be empty")
    private SettingsDto settings;

    private GameStatus status;

    private String winnerId;

    @NotNull(message = "Game status must not be null")
    private GameVisibility visibility;
}
