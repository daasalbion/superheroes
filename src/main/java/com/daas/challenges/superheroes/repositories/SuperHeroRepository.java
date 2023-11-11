package com.daas.challenges.superheroes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daas.challenges.superheroes.entities.SuperHero;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Integer> {
}
