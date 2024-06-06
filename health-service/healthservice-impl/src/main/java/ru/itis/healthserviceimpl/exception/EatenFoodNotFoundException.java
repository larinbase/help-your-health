package ru.itis.healthserviceimpl.exception;

import java.util.UUID;

public class EatenFoodNotFoundException extends NotFoundException {
    public EatenFoodNotFoundException(UUID id) {
        super("Eaten-Food with id = %s - not found".formatted(id));
    }
}
