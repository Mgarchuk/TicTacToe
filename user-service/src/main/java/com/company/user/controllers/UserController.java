package com.company.user.controllers;

import com.company.common.dtos.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        return null;
    }
}
