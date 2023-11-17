package com.daas.challenges.superheroes.services;

import java.util.List;

import com.daas.challenges.superheroes.dtos.SuperHeroDTO;

public interface SuperHeroService {

    List<SuperHeroDTO> list(String name);

    SuperHeroDTO get(Integer id);

    SuperHeroDTO create(SuperHeroDTO superHeroDTO);

    SuperHeroDTO update(Integer id, SuperHeroDTO superHeroDTO);

    SuperHeroDTO delete(Integer id);

}
