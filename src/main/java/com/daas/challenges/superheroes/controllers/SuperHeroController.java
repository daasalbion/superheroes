package com.daas.challenges.superheroes.controllers;

import static java.util.Objects.isNull;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daas.challenges.superheroes.dtos.SuperHeroDTO;
import com.daas.challenges.superheroes.services.SuperHeroService;

@RestController
@RequestMapping("/superheroes")
public class SuperHeroController {

    private final SuperHeroService superHeroService;

    public SuperHeroController(SuperHeroService superHeroService) {
        this.superHeroService = superHeroService;
    }

    @GetMapping
    public List<SuperHeroDTO> getAll(@RequestParam(value = "name", required = false) String name) {
        if (isNull(name)) {
            return superHeroService.getAll();
        } else {
            return superHeroService.findByName(name);
        }
    }

    @GetMapping("/{id}")
    public SuperHeroDTO get(@PathVariable("id") Integer id) {
        return superHeroService.get(id);
    }
}
