package com.daas.challenges.superheroes.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.daas.challenges.superheroes.dtos.SuperHeroDTO;
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
    public List<SuperHeroDTO> getAll() {
        return superHeroesRepository.findAll().stream()
                .map(SuperHeroDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<SuperHeroDTO> findByName(String name) {
        return superHeroesRepository.findSuperHeroesByNameContainingIgnoreCase(name)
                .stream()
                .map(SuperHeroDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public SuperHeroDTO get(Integer id) {
        return superHeroesRepository.findById(id)
                .map(SuperHeroDTO::new)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Superhero with id = %s doesn't exists",
                        id)));
    }

    @Override
    public SuperHeroDTO create(SuperHeroDTO superHeroDTO) {
        SuperHero newSuperHero = new SuperHero(superHeroDTO.getName(), superHeroDTO.getPower());
        SuperHero savedSuperHero = superHeroesRepository.save(newSuperHero);
        return new SuperHeroDTO(savedSuperHero);
    }

    @Override
    public SuperHeroDTO update(Integer id, SuperHeroDTO superHeroDTO) {
        SuperHero savedSuperHero = superHeroesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Superhero with id = %s doesn't exists",
                id)));
        SuperHero superHero = new SuperHero(savedSuperHero.getId(), superHeroDTO.getName(),
                superHeroDTO.getPower(), superHeroDTO.getStatus());
        SuperHero updatedSuperHero = superHeroesRepository.save(superHero);

        return new SuperHeroDTO(updatedSuperHero);
    }


}
