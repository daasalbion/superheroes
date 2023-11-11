package com.daas.challenges.superheroes.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.daas.challenges.superheroes.repositories.SuperHeroRepository;
import com.daas.challenges.superheroes.services.SuperHeroDTO;
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
                .map(s -> new SuperHeroDTO(s.getId(), s.getName(), s.getPower(), s.getStatus()))
                .collect(Collectors.toList());
    }
}
