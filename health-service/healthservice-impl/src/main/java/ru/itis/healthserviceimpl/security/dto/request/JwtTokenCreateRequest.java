package ru.itis.healthserviceimpl.security.dto.request;

import ru.itis.healthserviceimpl.model.roles.Role;

import java.util.List;

public record JwtTokenCreateRequest(String username, List<Role> roles) {
}
