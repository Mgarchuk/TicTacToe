package com.company.common.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    private UUID id;

    private boolean roomVisibility;

    @NotNull(message = "game id must not be empty")
    private int gameId;

    @NotNull(message = "creator id must not be empty")
    private int creatorId;

    //ToDo: add restrictions
    private String link;
}
