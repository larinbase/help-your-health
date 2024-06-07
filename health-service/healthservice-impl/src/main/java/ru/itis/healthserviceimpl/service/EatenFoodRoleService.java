package ru.itis.healthserviceimpl.service;

import java.util.UUID;

public interface EatenFoodRoleService {
    void create(UUID userId, UUID eatenFoodId);
}
