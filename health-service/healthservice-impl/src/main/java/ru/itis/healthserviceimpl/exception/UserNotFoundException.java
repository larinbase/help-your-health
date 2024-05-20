package ru.itis.healthserviceimpl.exception;

import java.util.UUID;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(UUID id) {
        super("user with user id %s not found".formatted(id));
    }
}
