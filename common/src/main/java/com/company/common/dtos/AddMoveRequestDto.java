package com.company.common.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AddMoveRequestDto {

    @Size(min = 3, max = 5, message = "description must have size between 1 and 5")
    @NotNull
    private String description;
}
