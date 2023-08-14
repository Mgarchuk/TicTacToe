package com.company.common.dtos;

import com.company.common.models.enums.GameVisibility;
import com.company.common.models.enums.PreferableSide;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateGameRequestDto {

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

    @Min(2)
    @Max(300)
    @NotNull
    @JsonProperty("move_time_limit")
    private Integer moveTimeLimit;

    @NotNull
    @JsonProperty("preferable_side")
    private PreferableSide preferableSide;

    @NotNull
    private GameVisibility visibility;
}
