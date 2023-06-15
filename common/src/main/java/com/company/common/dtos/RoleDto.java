package com.company.common.dtos;

import com.company.common.models.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Integer id;

    @NotNull(message = "role must not be null")
    private Role name;
}
