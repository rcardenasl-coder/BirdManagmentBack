package com.birdregistry.bird_registry_api.bird.domain.exception;

public class InvalidDateCreateException extends RuntimeException{
    public InvalidDateCreateException(String message) {
        super(message);
    }
}
