package com.daas.challenges.superheroes.dtos;

import com.daas.challenges.superheroes.entities.SuperHero;

import lombok.Getter;

@Getter
public class SuperHeroDTO {
    private final Integer id;
    private final String name;
    private final String power;
    private final String status;

    public SuperHeroDTO(Integer id, String name, String power, String status) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.status = status;
    }

    public SuperHeroDTO(SuperHero superHero) {
        this(superHero.getId(), superHero.getName(), superHero.getPower(), superHero.getStatus());
    }

}
