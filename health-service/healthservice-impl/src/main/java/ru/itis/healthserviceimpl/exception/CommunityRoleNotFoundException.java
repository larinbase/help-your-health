package ru.itis.healthserviceimpl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommunityRoleNotFoundException extends NotFoundException {
    public CommunityRoleNotFoundException(String role) {
        super("Role %s is not found".formatted(role));
    }
}

