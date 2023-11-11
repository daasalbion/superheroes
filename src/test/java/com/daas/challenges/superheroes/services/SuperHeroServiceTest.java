package com.daas.challenges.superheroes.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.daas.challenges.superheroes.dtos.SuperHeroDTO;
import com.daas.challenges.superheroes.entities.SuperHero;
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

    @Test
    void shouldReturnOneSuperHero() {
        when(superHeroesRepository.findById(1)).thenReturn(Optional.of(new SuperHero("Test", "Test")));
        SuperHeroDTO superHeroDTO = superHeroesService.get(1);
        Assertions.assertNotNull(superHeroDTO);
    }

    @Test
    void shouldReturnNotFound() {
        when(superHeroesRepository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> superHeroesService.get(1)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void shouldReturnOneSuperHeroByName() {
        String name = "test";
        when(superHeroesRepository.findSuperHeroesByNameContainingIgnoreCase(name))
                .thenReturn(List.of(new SuperHero("Test", "Test")));
        Assertions.assertEquals(1, superHeroesService.findByName(name).size());
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
    void shouldUpdateOneSuperHero() {
        SuperHero savedSuperHero = new SuperHero(1, "Test", "Test", "Active");
        SuperHero updatedSuperHero = new SuperHero(1, "Test", "Test", "Inactive");
        when(superHeroesRepository.findById(any())).thenReturn(Optional.of(savedSuperHero));
        when(superHeroesRepository.save(any())).thenReturn(updatedSuperHero);
        SuperHeroDTO superHeroDTORequest = new SuperHeroDTO(updatedSuperHero);
        SuperHeroDTO superHeroDTOResponse = superHeroesService.update(updatedSuperHero.getId(), superHeroDTORequest);
        Assertions.assertEquals(updatedSuperHero.getStatus(), superHeroDTOResponse.getStatus());
    }

    @Test
    void shouldDeleteOneSuperHero() {
        SuperHero savedSuperHero = new SuperHero(1, "Test", "Test", "Active");
        when(superHeroesRepository.findById(any())).thenReturn(Optional.of(savedSuperHero));
        SuperHeroDTO deletedSuperHeroDTO = superHeroesService.delete(savedSuperHero.getId());
        Assertions.assertEquals(savedSuperHero.getId(), deletedSuperHeroDTO.getId());
    }

    @Test
    void shouldReturnNotFoundWhenDelete() {
        when(superHeroesRepository.findById(1)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> superHeroesService.delete(1)).isInstanceOf(EntityNotFoundException.class);
    }

}
