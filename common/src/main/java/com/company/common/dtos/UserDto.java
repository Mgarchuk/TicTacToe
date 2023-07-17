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

    @NotNull
    @Positive
    private int rating;

    @NotBlank
    private String salt;

    @Size(min = 3, max = 264)
    private String email;

    @NotNull(message = "role id must not be empty")
    private Role role;
}
