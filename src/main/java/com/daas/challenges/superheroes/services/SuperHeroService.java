package com.daas.challenges.superheroes.services;

import java.util.List;
import java.util.Map;

import com.daas.challenges.superheroes.dtos.SuperHeroDTO;

public interface SuperHeroService {
    String NAME_FILTER = "name";

    List<SuperHeroDTO> getAll();

    List<SuperHeroDTO> findByName(String name);

    SuperHeroDTO get(Integer id);

    SuperHeroDTO create(SuperHeroDTO superHeroDTO);

    SuperHeroDTO update(Integer id, SuperHeroDTO superHeroDTO);

    SuperHeroDTO delete(Integer id);

    default List<SuperHeroDTO> list(Map<String, String> req) {
        if (req.isEmpty()) return getAll();

        if (req.containsKey(NAME_FILTER)) {
            return findByName(req.get(NAME_FILTER));
        }

        throw new IllegalArgumentException(String.format("%s is the filter supported", NAME_FILTER));
    }
}
