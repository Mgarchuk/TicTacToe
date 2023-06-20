package com.company.user.services;

import com.company.common.dtos.LoginRequestDto;
import com.company.common.dtos.SignUpRequestDto;
import com.company.common.exceptions.BadRequestException;
import com.company.common.models.UserEntity;
import com.company.common.models.enums.AuthProvider;
import com.company.common.models.enums.Role;
import com.company.common.repositories.UserRepository;
import com.company.user.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    public UserEntity registerUser(SignUpRequestDto signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use");
        }
        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setUsername(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER);
        user.setProvider(AuthProvider.LOCAL);

        return userRepository.save(user);
    }

    public String authenticate(LoginRequestDto loginRequest) {
        if (!userRepository.existsByEmail(loginRequest.getEmail())) {
            throw new BadRequestException("User with this email don't exists");
        }
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.createToken(authentication);
    }
}
