package ru.itis.healthserviceimpl.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.model.ExerciseSessionRole;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roles.ExerciseTemplateRoleType;
import ru.itis.healthserviceimpl.model.roles.Role;
import ru.itis.healthserviceimpl.repository.ExerciseSessionRoleRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.security.service.CommunityRoleService;
import ru.itis.healthserviceimpl.security.service.ExerciseSessionRoleService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("ExerciseSessionRoleService")
@RequiredArgsConstructor
public class ExerciseSessionRoleServiceImpl implements ExerciseSessionRoleService {

    private final UserRepository userRepository;
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
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        List<ExerciseSessionRole> exerciseSessionRoles =
                roleRepository.findByExerciseSessionEntityIdAndUserId(sessionId, user.getId());
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
