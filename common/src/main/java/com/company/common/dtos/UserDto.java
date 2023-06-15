package com.company.common.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;

    @NotBlank
    private String username;

    @NotNull
    @Positive
    private float rating;

    @NotBlank
    @Min(8)
    @Max(32)
    private String password;

    @NotBlank
    private String salt;

    @Size(min = 3, max = 264)
    private String email;

    @NotNull(message = "role id must not be empty")
    private int roleId;
}
