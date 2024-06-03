package ru.itis.healthserviceimpl.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roles.Role;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.security.service.CommunityRoleService;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;

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
}
