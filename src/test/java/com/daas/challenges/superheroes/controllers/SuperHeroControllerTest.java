package com.daas.challenges.superheroes.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class SuperHeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllSuperHeroes() throws Exception {
        this.mockMvc.perform(get("/superheroes"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOneSuperHero() throws Exception {
        this.mockMvc.perform(get("/superheroes/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFound() throws Exception {
        this.mockMvc.perform(get("/superheroes/100"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnSuperHeroesByName() throws Exception {
        this.mockMvc.perform(get("/superheroes?name=man"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get("/superheroes?badFilter=man"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
