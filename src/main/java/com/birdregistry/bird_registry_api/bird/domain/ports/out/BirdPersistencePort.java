package com.birdregistry.bird_registry_api.bird.domain.ports.out;

import com.birdregistry.bird_registry_api.bird.domain.model.Bird;

import java.util.List;

public interface BirdPersistencePort {
    List<Bird> findAll();
    Bird saveOrUpdate(Bird bird);
    boolean existsBird(Long id);
    void deleteById(Long id);
    Bird findById(Long id);
}
