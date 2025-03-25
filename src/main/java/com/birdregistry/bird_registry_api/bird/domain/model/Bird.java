package com.birdregistry.bird_registry_api.bird.domain.model;

import com.birdregistry.bird_registry_api.bird.domain.model.valueobjs.*;

public record Bird(
    Long id,
    Name name,
    Type type,
    Color color,
    DateCreate dateCreate,
    Description description
){
    public static BirdBuilder builder(){
        return new BirdBuilder();
    }

    public static class BirdBuilder{
        private Long id;
        private Name name;
        private Type type;
        private Color color;
        private DateCreate dateCreate;
        private Description description;

        public BirdBuilder id(Long id){
            this.id = id;
            return this;
        }
        public BirdBuilder name(Name name){
            this.name = name;
            return this;
        }
        public BirdBuilder type(Type type){
            this.type = type;
            return this;
        }
        public BirdBuilder color(Color color){
            this.color = color;
            return this;
        }
        public BirdBuilder dateCreate(DateCreate dateCreate){
            this.dateCreate = dateCreate;
            return this;
        }
        public BirdBuilder description(Description description){
            this.description = description;
            return this;
        }
        public Bird build(){
            return new Bird(id,name,type,color,dateCreate,description);
        }
    }
}