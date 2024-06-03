package ru.itis.healthserviceimpl.exception;

import java.util.UUID;

public class FoodNotFoundException extends NotFoundException {
    public FoodNotFoundException(UUID id) {
        super("Food with id = %s - not found".formatted(id));
    }
}
