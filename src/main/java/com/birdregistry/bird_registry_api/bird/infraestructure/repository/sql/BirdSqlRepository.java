package com.birdregistry.bird_registry_api.bird.infraestructure.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdSqlRepository extends JpaRepository<BirdData, Long> {
    boolean existsById(Long id);
}
