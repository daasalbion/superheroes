package com.daas.challenges.superheroes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daas.challenges.superheroes.entities.SuperHero;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Integer> {

    List<SuperHero> findSuperHeroesByNameContainingIgnoreCase(String name);
    boolean existsByName(String name);
}
