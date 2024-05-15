package ru.itis.healthserviceimpl.exception;

import java.util.UUID;

public class DrinkingWaterNotFoundeServiceException extends NotFoundServiceException{
    public DrinkingWaterNotFoundeServiceException(UUID id) {
        super("drinking water with user id %s not found".formatted(id));
    }
}
