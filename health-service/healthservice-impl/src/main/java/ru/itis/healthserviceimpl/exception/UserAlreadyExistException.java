package ru.itis.healthserviceimpl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistException extends AlreadyExistException {
    public UserAlreadyExistException(String username) {
        super("User with username %s is already exist".formatted(username));
    }
}

