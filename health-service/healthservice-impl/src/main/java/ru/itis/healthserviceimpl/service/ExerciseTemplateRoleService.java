package ru.itis.healthserviceimpl.service;

import java.util.UUID;

public interface ExerciseTemplateRoleService {
    void create(UUID userId, UUID exerciseTemplateEntityId);
}
