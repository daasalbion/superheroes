package com.daas.challenges.superheroes.services.impl;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daas.challenges.superheroes.dtos.LoginDTO;
import com.daas.challenges.superheroes.dtos.UserDTO;
import com.daas.challenges.superheroes.entities.Role;
import com.daas.challenges.superheroes.entities.User;
import com.daas.challenges.superheroes.repositories.RoleRepository;
import com.daas.challenges.superheroes.repositories.UserRepository;
import com.daas.challenges.superheroes.services.JwtProvider;
import com.daas.challenges.superheroes.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public String signIn(LoginDTO loginDTO) {
        LOGGER.info("New user attempting to sign in");
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with username = %s doesn't exists",
                        loginDTO.getUsername())));
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                        loginDTO.getPassword()));

        return jwtProvider.createToken(user.getUsername(), authentication.getAuthorities());
    }

    public UserDTO signup(UserDTO userDTO) {
        LOGGER.info("New user attempting to sign in");
        userRepository.findByUsername(userDTO.getUsername())
                .ifPresent(user -> {
                    throw new IllegalArgumentException(String.format("User with username = %s already exists",
                            user.getUsername()));
                });
        Role role = roleRepository.findByRoleName("ROLE_CSR").orElse(null);
        User user = userRepository.save(new User(userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword()),
                role,
                userDTO.getFirstName(),
                userDTO.getLastName()));

        return new UserDTO(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName());
    }

}