package com.daas.challenges.superheroes.services;

import com.daas.challenges.superheroes.dtos.LoginDTO;
import com.daas.challenges.superheroes.dtos.UserDTO;

public interface UserService {

    String signIn(LoginDTO loginDTO);
    UserDTO signup(UserDTO userDTO);
}
