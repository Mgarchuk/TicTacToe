package com.company.battle.utils.services;

import com.company.common.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserAuthorizationService {

    public UserDto getCurrentUser() {
        final Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8081/api/v1/user/by-email";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(jwt.getTokenValue());
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<UserDto> response = restTemplate
                .exchange(url, HttpMethod.GET, httpEntity, UserDto.class);
        UserDto user = response.getBody();
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There are no user with this email");
        }

        return user;
    }
}
