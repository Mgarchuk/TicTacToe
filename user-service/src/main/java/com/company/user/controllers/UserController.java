package com.company.user.controllers;

import com.company.common.dtos.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
public class UserController {

    @GetMapping
    List<UserDto> getAllUsers() {
        return null;
    }
}
