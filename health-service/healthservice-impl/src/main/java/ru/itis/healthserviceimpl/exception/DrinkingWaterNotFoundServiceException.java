package ru.itis.healthserviceimpl.exception;

import java.util.UUID;

public class DrinkingWaterNotFoundServiceException extends NotFoundException {
    public DrinkingWaterNotFoundServiceException(UUID id) {
        super("drinking water with user id %s not found".formatted(id));
    }
}
