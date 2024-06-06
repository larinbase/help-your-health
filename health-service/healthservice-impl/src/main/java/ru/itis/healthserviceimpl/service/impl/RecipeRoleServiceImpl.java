package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.RecipeNotFoundException;
import ru.itis.healthserviceimpl.exception.ServiceException;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.model.role.RecipeRole;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roletype.RecipeRoleType;
import ru.itis.healthserviceimpl.repository.RecipeRepository;
import ru.itis.healthserviceimpl.repository.RecipeRoleRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.service.RecipeRoleService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecipeRoleServiceImpl implements RecipeRoleService {
    private final RecipeRoleRepository recipeRoleRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Override
    public void create(UUID userId, UUID recipeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        if (!recipeRoleRepository.findByRecipeIdAndUserId(recipeId, userId).isEmpty()) {
            String message = "Role for user %s in recipe %s already exist";
            throw new ServiceException(message.formatted(userId, recipeId), HttpStatus.CONFLICT);
        }
        if (recipeRepository.findById(recipeId).isEmpty()) {
            throw new RecipeNotFoundException(recipeId);
        }
        RecipeRole role = RecipeRole.builder()
                .type(RecipeRoleType.EDITOR)
                .user(user)
                .recipeId(recipeId)
                .build();
        recipeRoleRepository.save(role);
    }
}
