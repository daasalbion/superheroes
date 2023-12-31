package com.daas.challenges.superheroes.dtos;

import javax.validation.constraints.NotEmpty;

import com.daas.challenges.superheroes.entities.SuperHero;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SuperHeroDTO {
    private final Integer id;
    @Schema(example = "Ironman")
    @NotEmpty(message = "name can't be empty")
    private final String name;
    @Schema(example = "Strength")
    @NotEmpty(message = "power can't be empty")
    private final String power;
    @Schema(example = "ACTIVE")
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
