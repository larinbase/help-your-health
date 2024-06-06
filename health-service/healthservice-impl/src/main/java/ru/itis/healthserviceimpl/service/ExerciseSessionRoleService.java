package ru.itis.healthserviceimpl.service;

import java.util.UUID;

public interface ExerciseSessionRoleService {
    void create(UUID userId, UUID exerciseSessionId);
}
