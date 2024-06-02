package ru.itis.healthserviceimpl.security.service;


import ru.itis.healthserviceimpl.model.roles.Role;

public interface CommunityRoleService {
    boolean hasAnyRole(Role... roles);
}
