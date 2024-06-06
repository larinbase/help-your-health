package ru.itis.healthserviceimpl.exception;

public class CommunityRoleNotFoundException extends NotFoundException {
    public CommunityRoleNotFoundException(String role) {
        super("Role %s is not found".formatted(role));
    }
}

