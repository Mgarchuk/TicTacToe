package com.company.user.controllers;

import com.company.common.dtos.AuthResponse;
import com.company.common.dtos.LoginRequestDto;
import com.company.common.dtos.SignUpRequestDto;
import com.company.common.dtos.UserDto;
import com.company.common.models.UserEntity;
import com.company.user.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign-up")
    ResponseEntity<UserDto> registerUser(@RequestBody SignUpRequestDto signUpRequest) {
        final UserEntity registeredUser = authService.registerUser(signUpRequest);
        final UserDto registeredUserDto = new UserDto();
        registeredUserDto.setEmail(registeredUser.getEmail());
        registeredUserDto.setId(registeredUser.getId());
        registeredUserDto.setUsername(registeredUser.getUsername());
        return new ResponseEntity<>(registeredUserDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDto loginRequest) {
        return ResponseEntity.ok(new AuthResponse(authService.authenticate(loginRequest)));
    }

}
