package com.daas.challenges.superheroes.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daas.challenges.superheroes.dtos.LoginDTO;
import com.daas.challenges.superheroes.dtos.UserDTO;
import com.daas.challenges.superheroes.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public String login(@RequestBody @Valid LoginDTO loginDTO) {
        return userService.signIn(loginDTO);
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO signup(@RequestBody @Valid UserDTO userDTO) {
        return userService.signup(userDTO);
    }

}