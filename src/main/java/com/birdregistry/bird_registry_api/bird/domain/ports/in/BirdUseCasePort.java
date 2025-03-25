package com.birdregistry.bird_registry_api.bird.domain.ports.in;

import com.birdregistry.bird_registry_api.bird.domain.model.Bird;

import java.util.List;

public interface BirdUseCasePort {
    List<Bird> getAll();
    Bird create(Bird bird);
    Bird update(Bird bird);
    void deleteById(Long id);
    Bird findById(Long id);
}
