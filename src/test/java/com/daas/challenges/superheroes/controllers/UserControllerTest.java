package com.daas.challenges.superheroes.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.daas.challenges.superheroes.dtos.LoginDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldLogin() throws Exception {
        LoginDTO loginDTO = new LoginDTO("admin", "letmein");
        this.mockMvc.perform(post("/users/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnForbidden() throws Exception {
        LoginDTO loginDTO = new LoginDTO("admin", "letmein3");
        this.mockMvc.perform(post("/users/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReturnNotFound() throws Exception {
        LoginDTO loginDTO = new LoginDTO("admin2", "letmein");
        this.mockMvc.perform(post("/users/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequest() throws Exception {
        LoginDTO loginDTO = new LoginDTO("admin2", null);
        this.mockMvc.perform(post("/users/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
