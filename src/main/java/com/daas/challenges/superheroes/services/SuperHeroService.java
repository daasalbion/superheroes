package com.daas.challenges.superheroes.services;

import java.util.List;

import com.daas.challenges.superheroes.dtos.SuperHeroDTO;

public interface SuperHeroService {

    List<SuperHeroDTO> getAll();
    SuperHeroDTO get(Integer id);
}
