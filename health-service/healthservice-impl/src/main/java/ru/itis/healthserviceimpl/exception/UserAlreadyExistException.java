package ru.itis.healthserviceimpl.exception;

public class UserAlreadyExistException extends AlreadyExistException {
    public UserAlreadyExistException(String username) {
        super("User with username %s is already exist".formatted(username));
    }
}

