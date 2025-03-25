package com.birdregistry.bird_registry_api.bird.infraestructure.repository.sql.mappers;

import com.birdregistry.bird_registry_api.bird.domain.model.Bird;
import com.birdregistry.bird_registry_api.bird.domain.model.valueobjs.*;
import com.birdregistry.bird_registry_api.bird.infraestructure.repository.sql.BirdData;
import org.springframework.stereotype.Component;

@Component
public class BirdDataMapper {
    public BirdData toData(Bird bird){
        return BirdData.builder()
                .id(bird.id())
                .name(bird.name().getValue())
                .type(bird.type().getValue())
                .color(bird.color().getValue())
                .dateCreate(bird.dateCreate().getValue())
                .description(bird.description().getValue())
                .build();
    }

    public Bird toDomain(BirdData birdData){
        return Bird.builder()
                .id(birdData.getId())
                .name(new Name(birdData.getName()))
                .type(new Type(birdData.getType()))
                .color(new Color(birdData.getColor()))
                .dateCreate(new DateCreate(birdData.getDateCreate()))
                .description(new Description(birdData.getDescription()))
                .build();
    }
}
