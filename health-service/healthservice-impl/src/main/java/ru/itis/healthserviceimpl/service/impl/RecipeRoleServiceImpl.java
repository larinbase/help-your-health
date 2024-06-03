package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.model.RecipeRole;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.repository.RecipeRoleRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.service.RecipeRoleService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecipeRoleServiceImpl implements RecipeRoleService {
    private final RecipeRoleRepository recipeRoleRepository;
    private final UserRepository userRepository;

    @Override
    public void create(UUID userId, UUID recipeId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));
        RecipeRole role = RecipeRole.builder()
                .user(user)
                .recipeId(recipeId)
                .build();
        recipeRoleRepository.save(role);
    }
}
