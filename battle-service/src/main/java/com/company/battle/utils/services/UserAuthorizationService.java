package com.company.battle.utils.services;

import com.company.common.models.UserEntity;
import com.company.common.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserAuthorizationService {

    @Autowired
    private final UserRepository userRepository;

    public UserEntity getCurrentUser() {
        final Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = jwt.getClaims().get("email").toString();
        UserEntity user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There are no user with this email");
        }

        return user;
    }
}
