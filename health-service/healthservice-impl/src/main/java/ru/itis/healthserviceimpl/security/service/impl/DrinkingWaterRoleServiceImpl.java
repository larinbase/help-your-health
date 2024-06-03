package ru.itis.healthserviceimpl.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.model.DrinkingWaterRole;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roles.DrinkingWaterRoleType;
import ru.itis.healthserviceimpl.model.roles.Role;
import ru.itis.healthserviceimpl.repository.DrinkingWaterRoleRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.security.service.CommunityRoleService;
import ru.itis.healthserviceimpl.security.service.DrinkingWaterRoleService;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("DrinkingWaterRoleService")
@RequiredArgsConstructor
public class DrinkingWaterRoleServiceImpl implements DrinkingWaterRoleService {

    private final DrinkingWaterRoleRepository roleRepository;
    private final CommunityRoleService communityRoleService;

    @Override
    public boolean hasAnyRoleByDrinkingWaterId(UUID drinkingWaterId, Role... roles) {
        if (communityRoleService.hasAnyRole(roles)) {
            return true;
        }
        if (drinkingWaterId == null) {
            return false;
        }
        BaseUserDetails principal = (BaseUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        List<DrinkingWaterRole> drinkingWaterRoles =
                roleRepository.findByDrinkingWaterIdAndUserId(drinkingWaterId, principal.getId());
        if (drinkingWaterRoles.isEmpty()) {
            for (Role role : roles) {
                if (DrinkingWaterRoleType.VIEWER.equals(role)) {
                    return true;
                }
            }
            return false;
        }
        for (Role role : roles) {
            for (DrinkingWaterRole drinkingWaterRole : drinkingWaterRoles) {
                if (drinkingWaterRole.getType().isIncludes(role)) {
                    return true;
                }
            }
        }
        return false;
    }
}
