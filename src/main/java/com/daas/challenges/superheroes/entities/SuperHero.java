package com.daas.challenges.superheroes.entities;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "superheroes")
public class SuperHero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String power;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SuperHero(String name, String power, String status) {
        this.name = name;
        this.power = power;
        this.status = status;
    }
}
