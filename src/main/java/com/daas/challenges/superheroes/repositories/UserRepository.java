package com.daas.challenges.superheroes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daas.challenges.superheroes.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);
}