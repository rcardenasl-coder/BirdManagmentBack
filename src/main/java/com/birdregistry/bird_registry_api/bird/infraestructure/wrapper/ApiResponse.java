package com.birdregistry.bird_registry_api.bird.infraestructure.wrapper;

public record ApiResponse<T>(String status, String message, T data) {
}
