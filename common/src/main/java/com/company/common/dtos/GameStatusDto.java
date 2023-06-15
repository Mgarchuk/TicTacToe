package com.company.common.dtos;

import com.company.common.models.enums.GameStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameStatusDto {

    private Integer id;

    @NotNull(message = "game status must not be null")
    private GameStatus name;
}
