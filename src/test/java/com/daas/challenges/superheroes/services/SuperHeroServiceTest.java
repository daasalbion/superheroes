package com.daas.challenges.superheroes.services;

import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.daas.challenges.superheroes.repositories.SuperHeroRepository;
import com.daas.challenges.superheroes.services.impl.SuperHeroServiceImpl;

class SuperHeroServiceTest {

    private final SuperHeroRepository superHeroesRepository = mock(SuperHeroRepository.class);
    private SuperHeroService superHeroesService;

    @BeforeEach
    void init() {
        this.superHeroesService = new SuperHeroServiceImpl(superHeroesRepository);
    }

    @Test
    void shouldReturnAllSuperHeroes() {
        List<SuperHeroDTO> superHeroes = superHeroesService.getAll();
        Assertions.assertTrue(superHeroes.isEmpty());
    }

}
