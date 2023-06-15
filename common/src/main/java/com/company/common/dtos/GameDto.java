package com.company.common.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    private int id;

    @NotNull(message = "settings id must not be empty")
    private int settingsId;

    @NotNull(message = "room id must not be empty")
    private int roomId;

    @NotNull(message = "game status id must not be empty")
    private int gameStatusId;
}
