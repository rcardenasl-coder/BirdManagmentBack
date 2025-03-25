package com.birdregistry.bird_registry_api.bird.application.mappers;

import com.birdregistry.bird_registry_api.bird.application.dto.BirdDto;
import com.birdregistry.bird_registry_api.bird.domain.model.Bird;
import com.birdregistry.bird_registry_api.bird.domain.model.valueobjs.*;
import org.springframework.stereotype.Component;

@Component
public class BirdDTOMapper {
    public BirdDto toDTO(Bird bird){
        return BirdDto.builder()
                .birdCode(bird.id())
                .name(bird.name().getValue())
                .type(bird.type().getValue())
                .color(bird.color().getValue())
                .dateCreate(bird.dateCreate().getValue())
                .description(bird.description().getValue())
                .build();
    }

    public Bird toDomain(BirdDto birdDto){
        return Bird.builder()
                .id(birdDto.birdCode())
                .name(new Name(birdDto.name()))
                .type(new Type(birdDto.type()))
                .color(new Color(birdDto.color()))
                .dateCreate(new DateCreate(birdDto.dateCreate()))
                .description(new Description(birdDto.description()))
                .build();
    }
}
