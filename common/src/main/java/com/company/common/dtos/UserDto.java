package com.company.common.dtos;

import com.company.common.models.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;

    @NotBlank
    private String username;

    @Positive
    private float rating;

    @NotBlank
    @Min(8)
    @Max(64)
    private String password;

    @Size(min = 3, max = 264)
    private String email;

    @NotNull(message = "role id must not be empty")
    private Role role;
}
