package com.daas.challenges.superheroes.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.daas.challenges.superheroes.dtos.SuperHeroDTO;
import com.daas.challenges.superheroes.entities.SuperHero;
import com.daas.challenges.superheroes.exceptions.IllegalArgumentException;
import com.daas.challenges.superheroes.exceptions.NotFoundException;
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
        List<SuperHeroDTO> superHeroes = superHeroesService.list(null);
        Assertions.assertTrue(superHeroes.isEmpty());
    }

    @Test
    void shouldReturnOneSuperHero() {
        when(superHeroesRepository.findById(1)).thenReturn(Optional.of(new SuperHero("Test", "Test")));
        SuperHeroDTO superHeroDTO = superHeroesService.get(1);
        Assertions.assertNotNull(superHeroDTO);
    }

    @Test
    void shouldReturnNotFound() {
        when(superHeroesRepository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> superHeroesService.get(1)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void shouldReturnOneSuperHeroByName() {
        String name = "test";
        when(superHeroesRepository.findSuperHeroesByNameContainingIgnoreCase(name))
                .thenReturn(List.of(new SuperHero("Test", "Test")));
        Assertions.assertEquals(1, superHeroesService.list(name).size());
    }

    @Test
    void shouldCreateOneSuperHero() {
        SuperHero savedSuperHero = new SuperHero("Test", "Test");
        when(superHeroesRepository.save(any())).thenReturn(savedSuperHero);
        SuperHeroDTO superHeroDTORequest = new SuperHeroDTO(savedSuperHero);
        SuperHeroDTO superHeroDTOResponse = superHeroesService.create(superHeroDTORequest);
        Assertions.assertEquals(superHeroDTORequest.getName(), superHeroDTOResponse.getName());
    }

    @Test
    void shouldReturnAlreadyCreated() {
        SuperHero savedSuperHero = new SuperHero("Test", "Test");
        SuperHeroDTO superHeroDTORequest = new SuperHeroDTO(savedSuperHero);
        when(superHeroesRepository.existsByName(savedSuperHero.getName())).thenReturn(true);
        assertThatThrownBy(() -> superHeroesService.create(superHeroDTORequest)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldUpdateOneSuperHero() {
        SuperHero savedSuperHero = new SuperHero(1, "Test", "Test", "ACTIVE");
        SuperHero updatedSuperHero = new SuperHero(1, "Test", "Test", "INACTIVE");
        when(superHeroesRepository.findById(any())).thenReturn(Optional.of(savedSuperHero));
        when(superHeroesRepository.save(any())).thenReturn(updatedSuperHero);
        SuperHeroDTO superHeroDTORequest = new SuperHeroDTO(updatedSuperHero);
        SuperHeroDTO superHeroDTOResponse = superHeroesService.update(updatedSuperHero.getId(), superHeroDTORequest);
        Assertions.assertEquals(updatedSuperHero.getStatus(), superHeroDTOResponse.getStatus());
    }

    @Test
    void shouldDeleteOneSuperHero() {
        SuperHero savedSuperHero = new SuperHero(1, "Test", "Test", "ACTIVE");
        when(superHeroesRepository.findById(any())).thenReturn(Optional.of(savedSuperHero));
        SuperHeroDTO deletedSuperHeroDTO = superHeroesService.delete(savedSuperHero.getId());
        Assertions.assertEquals(savedSuperHero.getId(), deletedSuperHeroDTO.getId());
    }

    @Test
    void shouldReturnNotFoundWhenDelete() {
        when(superHeroesRepository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> superHeroesService.delete(1)).isInstanceOf(NotFoundException.class);
    }

}
