package com.company.common.dtos;

import com.company.common.models.enums.GameStatus;
import com.company.common.models.enums.GameVisibility;
import jakarta.validation.Valid;
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

    @Valid
    @NotNull(message = "settings dto must not be empty")
    private SettingsDto settings;

    private GameStatus status;

    private String winnerId;

    @NotNull
    private GameVisibility visibility;
}
