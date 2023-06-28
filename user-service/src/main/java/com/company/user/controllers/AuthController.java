package com.company.user.controllers;

import com.company.common.dtos.UserDto;
import com.company.user.dtos.LogInRequestDto;
import com.company.user.dtos.SignUpRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @PostMapping("/sign-up")
    UserDto registerUser(@RequestBody SignUpRequestDto signUpRequest) {
        return null;
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LogInRequestDto loginRequest) {
        return null;
    }

    @GetMapping
    List<UserDto> getAllUsers() {
        return null;
    }
}
