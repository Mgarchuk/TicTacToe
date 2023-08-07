package com.company.common.dtos;

import com.company.common.models.enums.PreferableSide;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchGameRequestDto {

    @Min(3)
    @Max(100)
    @JsonProperty("square_size")
    private Integer squareSize;

    @Min(3)
    @Max(100)
    @JsonProperty("lines_count_for_win")
    private Integer linesCountForWin;

    @Min(2)
    @Max(300)
    @JsonProperty("move_time_limit")
    private Integer moveTimeLimit;

    @Min(1)
    @JsonProperty("game_count_limit")
    private Integer gameCountLimit;

    @NotNull
    @JsonProperty("preferable_side")
    private PreferableSide preferableSide;

}
