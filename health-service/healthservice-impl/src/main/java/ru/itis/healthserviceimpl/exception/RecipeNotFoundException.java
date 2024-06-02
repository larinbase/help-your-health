package ru.itis.healthserviceimpl.exception;

import java.util.UUID;

public class RecipeNotFoundException extends NotFoundException {

    public RecipeNotFoundException(UUID id) {
        super("Recipe with id = %s - not found".formatted(id));
    }
}
