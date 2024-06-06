package ru.itis.healthserviceimpl.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.model.role.ExerciseTemplateRole;
import ru.itis.healthserviceimpl.model.roletype.ExerciseTemplateRoleType;
import ru.itis.healthserviceimpl.model.roletype.Role;
import ru.itis.healthserviceimpl.repository.ExerciseTemplateRoleRepository;
import ru.itis.healthserviceimpl.security.service.CommunityRoleService;
import ru.itis.healthserviceimpl.security.service.ExerciseTemplateRoleService;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;

import java.util.List;
import java.util.UUID;

@Service("ExerciseTemplateRoleService")
@RequiredArgsConstructor
public class ExerciseTemplateRoleServiceImpl implements ExerciseTemplateRoleService {

    private final ExerciseTemplateRoleRepository roleRepository;
    private final CommunityRoleService communityRoleService;

    @Override
    public boolean hasAnyRoleByExerciseTemplateId(UUID exerciseTemplateEntityId, Role... roles) {
        if (communityRoleService.hasAnyRole(roles)) {
            return true;
        }
        if (exerciseTemplateEntityId == null) {
            return false;
        }
        BaseUserDetails principal = (BaseUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        List<ExerciseTemplateRole> exerciseTemplateRoles =
                roleRepository.findByExerciseTemplateEntityIdAndUserId(exerciseTemplateEntityId, principal.getId());
        if (exerciseTemplateRoles.isEmpty()) {
            for (Role role : roles) {
                if (ExerciseTemplateRoleType.VIEWER.equals(role)) {
                    return true;
                }
            }
            return false;
        }
        for (Role role : roles) {
            for (ExerciseTemplateRole exerciseTemplateRole : exerciseTemplateRoles) {
                if (exerciseTemplateRole.getType().isIncludes(role)) {
                    return true;
                }
            }
        }
        return false;
    }
}
