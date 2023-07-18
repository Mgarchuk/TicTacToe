package com.company.common.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @NotNull
    @JsonProperty("game_id")
    private String gameId;

    @NotNull
    @JsonProperty("user_id")
    private String userId;

    //ToDo: add regular expression and change min and max values
    //ToDo: check null
    @Size(min = 1, max = 5, message = "description must have size between 1 and 5")
    private String description;
}
