package com.daas.challenges.superheroes.configurations;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import com.daas.challenges.superheroes.services.CustomUserDetailsService;

public class JwtTokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);
    private static final String BEARER = "Bearer";

    private final CustomUserDetailsService userDetailsService;

    public JwtTokenFilter(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
        LOGGER.info("Process request to check for a JSON Web Token ");
        //Check for Authorization:Bearer JWT
        String headerValue = req.getHeader("Authorization");
        //Pull the Username and Roles from the JWT to construct the user details
        getBearerToken(headerValue)
                .flatMap(userDetailsService::loadUserByJwtToken)
                .ifPresent(userDetails -> SecurityContextHolder.getContext().setAuthentication(
                        new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities())));

        //move on to the next filter in the chains
        filterChain.doFilter(req, res);
    }

    private Optional<String> getBearerToken(String headerVal) {
        if (headerVal != null && headerVal.startsWith(BEARER)) {
            return Optional.of(headerVal.replace(BEARER, "").trim());
        }

        return Optional.empty();
    }
}