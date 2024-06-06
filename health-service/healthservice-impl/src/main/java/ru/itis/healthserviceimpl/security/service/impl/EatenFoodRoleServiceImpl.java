package ru.itis.healthserviceimpl.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.model.DrinkingWaterRole;
import ru.itis.healthserviceimpl.model.EatenFoodRole;
import ru.itis.healthserviceimpl.model.roles.DrinkingWaterRoleType;
import ru.itis.healthserviceimpl.model.roles.EatenFoodRoleType;
import ru.itis.healthserviceimpl.model.roles.Role;
import ru.itis.healthserviceimpl.repository.EatenFoodRoleRepository;
import ru.itis.healthserviceimpl.security.service.CommunityRoleService;
import ru.itis.healthserviceimpl.security.service.EatenFoodRoleService;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;

import java.util.List;
import java.util.UUID;

@Service("EatenFoodRoleService")
@RequiredArgsConstructor
public class EatenFoodRoleServiceImpl implements EatenFoodRoleService {

    private final CommunityRoleService communityRoleService;
    private final EatenFoodRoleRepository roleRepository;

    @Override
    public boolean hasAnyRoleByEatenFoodId(UUID eatenFoodId, Role... roles) {
        if (communityRoleService.hasAnyRole(roles)) {
            return true;
        }
        if (eatenFoodId == null) {
            return false;
        }
        BaseUserDetails principal = (BaseUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        List<EatenFoodRole> eatenFoodRoles =
                roleRepository.findByEatenFoodIdAndUserId(eatenFoodId, principal.getId());
        if (eatenFoodRoles.isEmpty()) {
            for (Role role : roles) {
                if (EatenFoodRoleType.VIEWER.equals(role)) {
                    return true;
                }
            }
            return false;
        }
        for (Role role : roles) {
            for (EatenFoodRole eatenFoodRole : eatenFoodRoles) {
                if (eatenFoodRole.getType().isIncludes(role)) {
                    return true;
                }
            }
        }
        return false;
    }
}
