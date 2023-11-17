package com.daas.challenges.superheroes.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daas.challenges.superheroes.annotations.ExecutionTimeLogger;
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
    @ExecutionTimeLogger
    public List<SuperHeroDTO> list(@RequestParam(required = false, value = "name") String name) {
        return superHeroService.list(name);
    }

    @GetMapping("/{id}")
    @ExecutionTimeLogger
    public SuperHeroDTO get(@PathVariable("id") Integer id) {
        return superHeroService.get(id);
    }

    @PostMapping
    @ExecutionTimeLogger
    @ResponseStatus(HttpStatus.CREATED)
    public SuperHeroDTO create(@RequestBody @Valid SuperHeroDTO superHeroDTO) {
        return superHeroService.create(superHeroDTO);
    }

    @PutMapping("/{id}")
    @ExecutionTimeLogger
    public SuperHeroDTO update(@PathVariable("id") Integer id, @RequestBody @Valid SuperHeroDTO superHeroDTO) {
        return superHeroService.update(id, superHeroDTO);
    }

    @DeleteMapping("/{id}")
    @ExecutionTimeLogger
    public SuperHeroDTO delete(@PathVariable("id") Integer id) {
        return superHeroService.delete(id);
    }

}
