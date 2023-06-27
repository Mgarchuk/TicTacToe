package com.company.user.controllers;

import com.company.common.dtos.UserDto;
import com.company.user.dtos.LogInRequestDto;
import com.company.user.dtos.SignUpRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @PostMapping("/sign-up")
    ResponseEntity<UserDto> registerUser(@RequestBody SignUpRequestDto signUpRequest) {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LogInRequestDto loginRequest) {
        return null;
    }

    @GetMapping
    ResponseEntity<List<UserDto>> getAllUsers() {
        return null;
    }
}
