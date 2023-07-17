package com.company.common.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDto {

    @Min(3)
    @Max(100)
    private int squareSize;

    @Min(3)
    @Max(100)
    private int linesCountForWin;

    private String xPlayerId;

    private String oPlayerId;

    //ToDo: Check null
    @Min(2)
    @Max(300)
    private int moveTimeLimit;
}
