package ru.itis.healthserviceimpl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistException extends ServiceException {

    public AlreadyExistException(String message) {
        super(message);
    }
}

