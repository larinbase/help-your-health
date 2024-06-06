package ru.itis.healthauthimpl.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends ServiceException {

    public AuthenticationException(String msg) {
        super(msg, HttpStatus.UNAUTHORIZED);
    }
}

