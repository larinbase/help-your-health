package ru.itis.healthserviceimpl.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.model.RecipeRole;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roles.RecipeRoleType;
import ru.itis.healthserviceimpl.model.roles.Role;
import ru.itis.healthserviceimpl.repository.RecipeRoleRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.security.service.CommunityRoleService;
import ru.itis.healthserviceimpl.security.service.RecipeRoleService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("RecipeRoleService")
@RequiredArgsConstructor
public class ExerciseTemplateRoleServiceImpl implements RecipeRoleService {

    private final UserRepository userRepository;
    private final RecipeRoleRepository recipeRoleRepository;
    private final CommunityRoleService communityRoleService;

    @Override
    public boolean hasAnyRoleByRecipeId(UUID recipeId, Role... roles) {
        if (communityRoleService.hasAnyRole(roles)){
            return true;
        }
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        Optional<List<RecipeRole>> recipeRolesOptional =
                recipeRoleRepository.findByRecipeIdAndUserId(recipeId, user.getId());
        if (recipeRolesOptional.isEmpty()) {
            for (Role role : roles) {
                if (RecipeRoleType.VIEWER.equals(role)) {
                    return true;
                }
            }
            return false;
        }
        List<RecipeRole> recipeRoles = recipeRolesOptional.get();
        for (Role role : roles) {
            for (RecipeRole recipeRole : recipeRoles) {
                if (recipeRole.getType().isIncludes(role)) {
                    return true;
                }
            }
        }
        return false;
    }
}
