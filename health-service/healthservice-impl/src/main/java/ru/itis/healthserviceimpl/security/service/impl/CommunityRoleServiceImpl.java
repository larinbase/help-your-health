package ru.itis.healthserviceimpl.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roletype.CommunityRoleType;
import ru.itis.healthserviceimpl.model.roletype.Role;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.security.service.CommunityRoleService;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;

import java.util.UUID;

@Service("CommunityRoleService")
@RequiredArgsConstructor
@Slf4j
public class CommunityRoleServiceImpl implements CommunityRoleService {

    private final UserRepository userRepository;

    @Override
    public boolean hasAnyRole(Role... roles) {
        BaseUserDetails principal = (BaseUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UserNotFoundException(principal.getUsername()));
        log.info("current user: {}", user.getUsername());
        Role communityRole = user.getRole().getType();
        log.info("user role: {}", communityRole);
        for (Role role : roles) {
            log.info("current role for check {}", role);
            if (communityRole.isIncludes(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canUpdateRole(UUID id, String role) {
        BaseUserDetails principal = (BaseUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal.getId().equals(id)) {
            return false;
        }
        User currentUser = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UserNotFoundException(principal.getUsername()));
        User targetUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        CommunityRoleType currentUserRoleType = currentUser.getRole().getType();
        CommunityRoleType targetUserRoleType = targetUser.getRole().getType();
        CommunityRoleType targetUserNewRoleType = CommunityRoleType.valueOf(role);
        return currentUserRoleType.isIncludes(targetUserRoleType) && // 1
                currentUserRoleType.isIncludes(targetUserNewRoleType); // 2
        // текущий пользователь тот, кто делает запрос. целевой пользователь тот, кому обновляют роль
        // 1. текущий пользователь может обновить только того целевого пользователя, чья роль не выше по иерархии
        // 2. новая роль целевого пользователя по иерархии не может быть выше роли текущего пользователя
    }
}
