package ru.itis.healthserviceimpl.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.model.role.ExerciseSessionRole;
import ru.itis.healthserviceimpl.model.roletype.ExerciseTemplateRoleType;
import ru.itis.healthserviceimpl.model.roletype.Role;
import ru.itis.healthserviceimpl.repository.ExerciseSessionRoleRepository;
import ru.itis.healthserviceimpl.security.service.CommunityRoleService;
import ru.itis.healthserviceimpl.security.service.ExerciseSessionRoleService;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;

import java.util.List;
import java.util.UUID;

@Service("ExerciseSessionRoleService")
@RequiredArgsConstructor
public class ExerciseSessionRoleServiceImpl implements ExerciseSessionRoleService {

    private final ExerciseSessionRoleRepository roleRepository;
    private final CommunityRoleService communityRoleService;

    @Override
    public boolean hasAnyRoleByExerciseSessionId(UUID sessionId, Role... roles) {
        if (communityRoleService.hasAnyRole(roles)) {
            return true;
        }
        if (sessionId == null) {
            return false;
        }
        BaseUserDetails principal = (BaseUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        List<ExerciseSessionRole> exerciseSessionRoles =
                roleRepository.findByExerciseSessionEntityIdAndUserId(sessionId, principal.getId());
        if (exerciseSessionRoles.isEmpty()) {
            for (Role role : roles) {
                if (ExerciseTemplateRoleType.VIEWER.equals(role)) {
                    return true;
                }
            }
            return false;
        }
        for (Role role : roles) {
            for (ExerciseSessionRole exerciseSessionRole : exerciseSessionRoles) {
                if (exerciseSessionRole.getType().isIncludes(role)) {
                    return true;
                }
            }
        }
        return false;
    }
}
