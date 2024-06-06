package ru.itis.healthserviceimpl.service;

import java.util.UUID;

public interface DrinkingWaterRoleService {
    void create(UUID userId, UUID drinkingWaterId);
}
