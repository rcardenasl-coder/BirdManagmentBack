package com.birdregistry.bird_registry_api.bird.application.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder(toBuilder = true)
public record BirdDto(Long birdCode,
                      String name,
                      String type,
                      String color,
                      LocalDate dateCreate,
                      String description) {
}
