package com.birdregistry.bird_registry_api.bird.domain.usecase;

import com.birdregistry.bird_registry_api.bird.config.utils.Constants;
import com.birdregistry.bird_registry_api.bird.domain.exception.NotFoundBirdException;
import com.birdregistry.bird_registry_api.bird.domain.model.Bird;
import com.birdregistry.bird_registry_api.bird.domain.ports.in.BirdUseCasePort;
import com.birdregistry.bird_registry_api.bird.domain.ports.out.BirdPersistencePort;

import java.util.List;

public class BirdUseCase implements BirdUseCasePort {
    private final BirdPersistencePort persistencePort;

    public BirdUseCase(BirdPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public List<Bird> getAll() {
        return persistencePort.findAll();
    }

    @Override
    public Bird create(Bird bird) {
        if (bird.id() != null){
            throw new IllegalArgumentException(Constants.NOT_ID_CREATING);
        }
        return persistencePort.saveOrUpdate(bird);

    }

    @Override
    public Bird update(Bird bird) {
        validateExists(bird.id());
        return persistencePort.saveOrUpdate(bird);
    }

    @Override
    public void deleteById(Long id) {
        validateExists(id);
        persistencePort.deleteById(id);
    }

    @Override
    public Bird findById(Long id) {
        validateExists(id);
        return persistencePort.findById(id);
    }

    private void validateExists(Long id){
        boolean exists = persistencePort.existsBird(id);
        if (!exists){
            throw new NotFoundBirdException(Constants.NOT_FOUND_BIRD);
        }
    }
}
