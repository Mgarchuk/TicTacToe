package com.company.user.services;


import com.company.user.dtos.LogInRequestDto;
import com.company.user.dtos.SignUpRequestDto;
import com.company.common.models.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public UserEntity registerUser(SignUpRequestDto signUpRequest) {
        return null;
    }

    public String authenticate(LogInRequestDto loginRequest) {
        return null;
    }
}
