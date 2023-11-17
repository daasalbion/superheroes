package com.daas.challenges.superheroes.dtos;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO extends LoginDTO {
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    public UserDTO(Long id, String username, String firstName, String lastName) {
        super(username);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
