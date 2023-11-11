package com.daas.challenges.superheroes.dtos;

import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String message;
    private final List<String> details;

    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }

    public ErrorResponse(String message) {
        this(message, Collections.emptyList());
    }
}
