package com.daas.challenges.superheroes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/superheroes")
public class SuperHeroesController {

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello, World";
    }
}
