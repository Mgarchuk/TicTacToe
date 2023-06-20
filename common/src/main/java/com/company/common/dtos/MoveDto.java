package com.company.common.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveDto {

    private UUID id;

    @NotNull(message = "game id must not be empty")
    private int gameId;

    @NotNull(message = "user id must not be empty")
    private int userId;

    //ToDo: add regular expression and change min and max values
    @Size(min = 1, max = 10, message = "description must have size between 1 and 10")
    private String description;
}
