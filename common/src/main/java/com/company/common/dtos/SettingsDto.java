package com.company.common.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDto {

    @Min(3)
    @Max(100)
    @NotNull
    @JsonProperty("square_size")
    private Integer squareSize;

    @Min(3)
    @Max(100)
    @NotNull
    @JsonProperty("lines_count_for_win")
    private Integer linesCountForWin;

    @JsonProperty("x_player_id")
    private String xPlayerId;

    @JsonProperty("o_player_id")
    private String oPlayerId;

    @Min(2)
    @Max(300)
    @NotNull
    @JsonProperty("move_time_limit")
    private Integer moveTimeLimit;
}
