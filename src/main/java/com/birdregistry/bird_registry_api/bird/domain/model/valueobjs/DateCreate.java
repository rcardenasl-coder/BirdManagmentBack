package com.birdregistry.bird_registry_api.bird.domain.model.valueobjs;

import com.birdregistry.bird_registry_api.bird.config.utils.Constants;
import com.birdregistry.bird_registry_api.bird.domain.exception.InvalidDateCreateException;

import java.time.LocalDate;

public class DateCreate {
    private final LocalDate value;

    public DateCreate(LocalDate value) {
        if (value == null){
            throw new InvalidDateCreateException(String.format(Constants.VALUE_DATE_NULL,"DateCreate"));
        }
        if (value.isAfter(LocalDate.now())){
            throw new InvalidDateCreateException(String.format(Constants.VALUE_DATE_FUTURE,"DateCreate"));
        }
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }
}
