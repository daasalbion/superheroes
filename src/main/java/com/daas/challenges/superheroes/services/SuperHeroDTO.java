package com.daas.challenges.superheroes.services;

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

}
