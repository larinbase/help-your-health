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
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;

import java.util.List;
import java.util.UUID;

@Service("RecipeRoleService")
@RequiredArgsConstructor
public class RecipeRoleServiceImpl implements RecipeRoleService {

    private final RecipeRoleRepository recipeRoleRepository;
    private final CommunityRoleService communityRoleService;

    @Override
    public boolean hasAnyRoleByRecipeId(UUID recipeId, Role... roles) {
        if (communityRoleService.hasAnyRole(roles)) {
            return true;
        }
        if (recipeId == null) {
            return false;
        }
        BaseUserDetails principal = (BaseUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        List<RecipeRole> recipeRoles =
                recipeRoleRepository.findByRecipeIdAndUserId(recipeId, principal.getId());
        if (recipeRoles.isEmpty()) {
            for (Role role : roles) {
                if (RecipeRoleType.VIEWER.equals(role)) {
                    return true;
                }
            }
            return false;
        }
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
