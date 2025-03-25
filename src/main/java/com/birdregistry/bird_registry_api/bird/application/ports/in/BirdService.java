package com.birdregistry.bird_registry_api.bird.application.ports.in;

import com.birdregistry.bird_registry_api.bird.application.dto.BirdDto;

import java.util.List;

public interface BirdService {
    List<BirdDto> getAll();
    BirdDto create(BirdDto birdDto);
    BirdDto update(BirdDto birdDto);
    void delete(Long id);
    BirdDto findById(Long id);
}
