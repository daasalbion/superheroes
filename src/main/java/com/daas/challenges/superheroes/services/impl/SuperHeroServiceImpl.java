package com.daas.challenges.superheroes.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daas.challenges.superheroes.entities.SuperHero;
import com.daas.challenges.superheroes.repositories.SuperHeroRepository;
import com.daas.challenges.superheroes.services.SuperHeroService;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {

    private final SuperHeroRepository superHeroesRepository;

    public SuperHeroServiceImpl(SuperHeroRepository superHeroesRepository) {
        this.superHeroesRepository = superHeroesRepository;
    }

    @Override
    public List<SuperHero> getAll() {
        return superHeroesRepository.findAll();
    }
}
