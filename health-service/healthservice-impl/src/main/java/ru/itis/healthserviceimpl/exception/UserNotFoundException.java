package ru.itis.healthserviceimpl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String username) {
        super("User with username %s is not found".formatted(username));
    }

    public UserNotFoundException(UUID id) {
        super("User with id %s is not found".formatted(id));
    }
}

