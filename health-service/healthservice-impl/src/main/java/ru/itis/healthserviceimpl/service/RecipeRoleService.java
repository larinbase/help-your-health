package ru.itis.healthserviceimpl.service;

import ru.itis.healthserviceimpl.model.User;

import java.util.UUID;

public interface RecipeRoleService {
    void create(UUID userId, UUID recipeId);
}
