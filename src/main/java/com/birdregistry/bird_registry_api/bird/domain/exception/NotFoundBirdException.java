package com.birdregistry.bird_registry_api.bird.domain.exception;

public class NotFoundBirdException extends RuntimeException{
    public NotFoundBirdException(String message) {
        super(message);
    }
}
