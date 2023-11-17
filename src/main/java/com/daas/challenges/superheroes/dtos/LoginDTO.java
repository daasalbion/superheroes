package com.daas.challenges.superheroes.dtos;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDTO {
    @NotNull
    @Schema(example = "admin")
    private String username;
    @NotNull
    @Schema(example = "letmein")
    private String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDTO(String username) {
        this(username, null);
    }
}
