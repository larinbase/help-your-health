package ru.itis.healthserviceimpl.exception;

import java.util.UUID;


public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String username) {
        super("User with username %s is not found".formatted(username));
    }

    public UserNotFoundException(UUID id) {
        super("User with id %s is not found".formatted(id));
    }
}

