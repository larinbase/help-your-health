package ru.itis.healthserviceimpl.security.service;


import ru.itis.healthserviceimpl.model.roles.Role;

import java.util.UUID;

public interface ExerciseTemplateRoleService {
    boolean hasAnyRoleByExerciseTemplateId(UUID recipeId, Role... roles);
}
