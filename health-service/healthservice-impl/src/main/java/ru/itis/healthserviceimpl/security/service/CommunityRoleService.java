package ru.itis.healthserviceimpl.security.service;


import ru.itis.healthserviceimpl.model.roles.Role;

import java.util.UUID;

public interface CommunityRoleService {
    boolean hasAnyRole(Role... roles);

    boolean canUpdateRole(UUID id, String role);
}
