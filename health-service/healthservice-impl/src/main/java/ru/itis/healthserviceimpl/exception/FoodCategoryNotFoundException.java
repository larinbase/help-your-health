package ru.itis.healthserviceimpl.exception;

import java.util.UUID;

public class FoodCategoryNotFoundException extends NotFoundException {
    public FoodCategoryNotFoundException(UUID id) {
        super("Food-Category with id = %s - not found".formatted(id));
    }
}
