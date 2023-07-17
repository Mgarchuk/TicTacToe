package com.company.common.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveDto {

    private String id;
    private LocalDateTime creationDate;

    @NotNull(message = "game id must not be empty")
    private String gameId;

    @NotNull(message = "user id must not be empty")
    private String userId;

    //ToDo: add regular expression and change min and max values
    @Size(min = 1, max = 10, message = "description must have size between 1 and 10")
    private String description;
}
