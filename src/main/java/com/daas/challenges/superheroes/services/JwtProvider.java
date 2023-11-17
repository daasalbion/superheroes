package com.daas.challenges.superheroes.services;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public interface JwtProvider {

    String createToken(String username, Collection<? extends GrantedAuthority> roles);

    boolean isValidToken(String token);

    String getUsername(String token);

    List<GrantedAuthority> getRoles(String token);
}
