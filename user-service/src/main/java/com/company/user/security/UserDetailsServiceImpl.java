package com.company.user.security;

import com.company.common.models.UserEntity;
import com.company.common.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User " + email + " not found"));
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(UUID id) throws UsernameNotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User " + id + " not found"));
        return UserPrincipal.create(user);
    }
}
