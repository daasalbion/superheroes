package com.daas.challenges.superheroes.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.daas.challenges.superheroes.dtos.SuperHeroDTO;
import com.daas.challenges.superheroes.entities.SuperHero;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class SuperHeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    void shouldCreateNewSuperHero() throws Exception {
        SuperHero superHero = new SuperHero("test", "test");
        SuperHeroDTO superHeroDTO = new SuperHeroDTO(superHero);
        this.mockMvc.perform(post("/superheroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHeroDTO)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnErrorForIncorrectRequest() throws Exception {
        SuperHero superHero = new SuperHero(null, "test");
        SuperHeroDTO superHeroDTO = new SuperHeroDTO(superHero);
        this.mockMvc.perform(post("/superheroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHeroDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateSuperHero() throws Exception {
        SuperHero superHero = new SuperHero(1, "test", "test", "ACTIVE");
        SuperHeroDTO superHeroDTO = new SuperHeroDTO(superHero);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/superheroes/{id}", superHeroDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHeroDTO)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundWhenUpdate() throws Exception {
        SuperHero superHero = new SuperHero(1000, "test", "test", "ACTIVE");
        SuperHeroDTO superHeroDTO = new SuperHeroDTO(superHero);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/superheroes/{id}", superHeroDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHeroDTO)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequestWhenUpdate() throws Exception {
        SuperHero superHero = new SuperHero(1, null, "test", "ACTIVE");
        SuperHeroDTO superHeroDTO = new SuperHeroDTO(superHero);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/superheroes/{id}", superHeroDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superHeroDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnDeleteOneSuperHero() throws Exception {
        SuperHero superHero = new SuperHero(2, null, "test", "ACTIVE");
        SuperHeroDTO superHeroDTO = new SuperHeroDTO(superHero);
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/superheroes/{id}", superHeroDTO.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundWhenDelete() throws Exception {
        SuperHero superHero = new SuperHero(1000, "test", "test", "ACTIVE");
        SuperHeroDTO superHeroDTO = new SuperHeroDTO(superHero);
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/superheroes/{id}", superHeroDTO.getId()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


}
