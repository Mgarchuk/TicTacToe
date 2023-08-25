package com.company.user.controllers;

import com.company.common.dtos.UserDto;
import com.company.user.models.UserEntity;
import com.company.user.mapper.UserMapper;
import com.company.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        UserEntity user = userService.getById(id);
        return userMapper.toDTO(user);
    }

    @GetMapping("/by-email")
    public UserDto getUserByEmail() {
        final Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = jwt.getClaims().get("email").toString();
        UserEntity user = userService.getByEmail(email);
        return userMapper.toDTO(user);
    }

    @PostMapping("/registration")
    public UserDto registration() {
        UserEntity user = userService.registration();
        return userMapper.toDTO(user);
    }
}
