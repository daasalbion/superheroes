package com.daas.challenges.superheroes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daas.challenges.superheroes.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String name);
}
