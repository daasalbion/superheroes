package com.daas.challenges.superheroes.controllers;

import java.util.List;
import java.util.Map;
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

import com.daas.challenges.superheroes.dtos.SuperHeroDTO;
import com.daas.challenges.superheroes.services.SuperHeroService;

@RestController
@RequestMapping("/superheroes")
public class SuperHeroController {

    private static final String NAME_FILTER = "name";
    private final SuperHeroService superHeroService;

    public SuperHeroController(SuperHeroService superHeroService) {
        this.superHeroService = superHeroService;
    }

    @GetMapping
    public List<SuperHeroDTO> list(@RequestParam(required = false) Map<String, String> req) {
        if (req.isEmpty()) return superHeroService.getAll();

        if (req.containsKey(NAME_FILTER)) {
            return superHeroService.findByName(req.get(NAME_FILTER));
        }

        throw new IllegalArgumentException(String.format("%s is the filter supported", NAME_FILTER));
    }

    @GetMapping("/{id}")
    public SuperHeroDTO get(@PathVariable("id") Integer id) {
        return superHeroService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SuperHeroDTO create(@RequestBody @Valid SuperHeroDTO superHeroDTO) {
        return superHeroService.create(superHeroDTO);
    }

    @PutMapping("/{id}")
    public SuperHeroDTO update(@PathVariable("id") Integer id, @RequestBody @Valid SuperHeroDTO superHeroDTO) {
        return superHeroService.update(id, superHeroDTO);
    }

    @DeleteMapping("/{id}")
    public SuperHeroDTO delete(@PathVariable("id") Integer id) {
        return superHeroService.delete(id);
    }

}
