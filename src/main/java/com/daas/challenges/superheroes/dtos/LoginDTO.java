package com.daas.challenges.superheroes.dtos;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public LoginDTO(String username) {
        this.username = username;
    }
}
