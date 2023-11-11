package com.daas.challenges.superheroes.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.daas.challenges.superheroes.repositories.SuperHeroRepository;
import com.daas.challenges.superheroes.dtos.SuperHeroDTO;
import com.daas.challenges.superheroes.services.SuperHeroService;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {

    private final SuperHeroRepository superHeroesRepository;

    public SuperHeroServiceImpl(SuperHeroRepository superHeroesRepository) {
        this.superHeroesRepository = superHeroesRepository;
    }

    @Override
    public List<SuperHeroDTO> getAll() {
        return superHeroesRepository.findAll().stream()
                .map(SuperHeroDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public SuperHeroDTO get(Integer id) {
        return superHeroesRepository.findById(id)
                .map(SuperHeroDTO::new)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Superhero with id = %s doesn't exists",
                        id)));
    }
}
