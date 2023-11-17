package com.daas.challenges.superheroes.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {

    Optional<UserDetails> loadUserByJwtToken(String jwtToken);
}
