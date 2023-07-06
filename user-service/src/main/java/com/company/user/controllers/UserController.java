package com.company.user.controllers;

import com.company.common.dtos.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @GetMapping("/{id}")
    UserDto getUserById(@PathVariable UUID id) {
        return null;
    }
}
