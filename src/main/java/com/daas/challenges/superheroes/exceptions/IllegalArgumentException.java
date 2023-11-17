package com.daas.challenges.superheroes.exceptions;

import lombok.Getter;

@Getter
public class IllegalArgumentException extends RuntimeException {

    public IllegalArgumentException(String message) {
        super(message);
    }

}
