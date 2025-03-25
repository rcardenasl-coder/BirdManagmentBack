package com.birdregistry.bird_registry_api.bird.application.service;

import com.birdregistry.bird_registry_api.bird.application.dto.BirdDto;
import com.birdregistry.bird_registry_api.bird.application.mappers.BirdDTOMapper;
import com.birdregistry.bird_registry_api.bird.application.ports.in.BirdService;
import com.birdregistry.bird_registry_api.bird.domain.ports.in.BirdUseCasePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BirdServiceImpl implements BirdService {
    private final BirdUseCasePort casePort;
    private final BirdDTOMapper dtoMapper;

    public BirdServiceImpl(BirdUseCasePort casePort, BirdDTOMapper dtoMapper) {
        this.casePort = casePort;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<BirdDto> getAll() {
        return casePort.getAll().stream().map(dtoMapper::toDTO).toList();
    }

    @Override
    public BirdDto create(BirdDto birdDto) {
        return dtoMapper.toDTO(casePort.create(dtoMapper.toDomain(birdDto)));
    }

    @Override
    public BirdDto update(BirdDto birdDto) {
        return dtoMapper.toDTO(casePort.update(dtoMapper.toDomain(birdDto)));
    }

    @Override
    public void delete(Long id) {
        casePort.deleteById(id);
    }

    @Override
    public BirdDto findById(Long id) {
        return dtoMapper.toDTO(casePort.findById(id));
    }
}
