package com.daas.challenges.superheroes.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daas.challenges.superheroes.entities.SuperHero;
import com.daas.challenges.superheroes.services.SuperHeroService;

@RestController
@RequestMapping("/superheroes")
public class SuperHeroController {

    private final SuperHeroService superHeroService;

    public SuperHeroController(SuperHeroService superHeroService) {
        this.superHeroService = superHeroService;
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello, World";
    }

    @GetMapping
    public List<SuperHero> getAll() {
        return superHeroService.getAll();
    }
}
