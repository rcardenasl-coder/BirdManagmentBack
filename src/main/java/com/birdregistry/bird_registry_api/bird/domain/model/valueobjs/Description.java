package com.birdregistry.bird_registry_api.bird.domain.model.valueobjs;

import com.birdregistry.bird_registry_api.bird.config.utils.Constants;

public class Description {

    private final String value;

    public Description(String value) {
        if (value == null || value.isBlank()){
            throw new IllegalArgumentException(String.format(Constants.VALUE_NOT_EMPTY_OR_NULL,"Description"));
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
