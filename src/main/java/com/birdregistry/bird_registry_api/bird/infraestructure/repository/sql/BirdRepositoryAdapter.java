package com.birdregistry.bird_registry_api.bird.infraestructure.repository.sql;

import com.birdregistry.bird_registry_api.bird.domain.model.Bird;
import com.birdregistry.bird_registry_api.bird.domain.ports.out.BirdPersistencePort;
import com.birdregistry.bird_registry_api.bird.infraestructure.repository.sql.mappers.BirdDataMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BirdRepositoryAdapter implements BirdPersistencePort {
    private final BirdSqlRepository repository;
    private final BirdDataMapper mapper;

    public BirdRepositoryAdapter(BirdSqlRepository repository, BirdDataMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Bird> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Bird saveOrUpdate(Bird bird) {
        return mapper.toDomain(repository.save(mapper.toData(bird)));
    }

    @Override
    public boolean existsBird(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Bird findById(Long id) {
        return mapper.toDomain(repository.findById(id).get());
    }

}
