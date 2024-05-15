package ru.itis.healthserviceimpl.exception;

import org.bson.types.ObjectId;

public class RecipeNotFoundException extends NotFoundException {

    public RecipeNotFoundException(ObjectId id) {
        super("Recipe with id = %s - not found".formatted(id));
    }
}
