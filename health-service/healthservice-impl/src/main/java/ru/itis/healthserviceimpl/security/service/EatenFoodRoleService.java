package ru.itis.healthserviceimpl.security.service;

import ru.itis.healthserviceimpl.model.roles.Role;

import java.util.UUID;

public interface EatenFoodRoleService {
    boolean hasAnyRoleByEatenFoodId(UUID eatenFoodId, Role... roles);
}
