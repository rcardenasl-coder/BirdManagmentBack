package com.birdregistry.bird_registry_api.bird.config.beans;

import com.birdregistry.bird_registry_api.bird.domain.ports.in.BirdUseCasePort;
import com.birdregistry.bird_registry_api.bird.domain.ports.out.BirdPersistencePort;
import com.birdregistry.bird_registry_api.bird.domain.usecase.BirdUseCase;
import com.birdregistry.bird_registry_api.bird.infraestructure.repository.sql.BirdRepositoryAdapter;
import com.birdregistry.bird_registry_api.bird.infraestructure.repository.sql.BirdSqlRepository;
import com.birdregistry.bird_registry_api.bird.infraestructure.repository.sql.mappers.BirdDataMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BirdConfigurationBean {
    private final BirdSqlRepository birdSqlRepository;
    private final BirdDataMapper dataMapper;

    public BirdConfigurationBean(BirdSqlRepository birdSqlRepository, BirdDataMapper dataMapper) {
        this.birdSqlRepository = birdSqlRepository;
        this.dataMapper = dataMapper;
    }

    @Bean
    public BirdUseCasePort birdUseCasePort(){
        return new BirdUseCase(birdPersistencePort());
    }

    @Bean
    public BirdPersistencePort birdPersistencePort(){
       return new BirdRepositoryAdapter(birdSqlRepository,dataMapper);
    }
}
