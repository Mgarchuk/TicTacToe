package com.company.common.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveDto {

    private String id;
    private LocalDateTime creationDate;

    @UUID
    @NotNull
    @JsonProperty("game_id")
    private String gameId;

    @NotNull
    @JsonProperty("user_id")
    private String userId;

    @Size(min = 3, max = 5, message = "description must have size between 1 and 5")
    @NotNull
    private String description;
}