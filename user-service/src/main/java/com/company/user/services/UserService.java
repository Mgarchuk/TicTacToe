package com.company.user.services;

import com.company.user.models.UserEntity;
import com.company.common.models.enums.Role;
import com.company.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserEntity getById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public UserEntity registration() {
        final Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = jwt.getClaims().get("email").toString();

        UserEntity user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with the same email is already registered");
        }
        user = new UserEntity();
        user.setCreationDate(LocalDateTime.now());
        user.setEmail(email);
        user.setUsername(email);
        user.setRole(Role.USER);
        user.setRating(1500);
        user.setAlive(true);
        return userRepository.save(user);
    }
}
