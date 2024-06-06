package ru.itis.healthserviceimpl.security.service;


import ru.itis.healthserviceimpl.model.roletype.Role;

import java.util.UUID;

public interface ExerciseSessionRoleService {
    boolean hasAnyRoleByExerciseSessionId(UUID sessionId, Role... roles);
}
