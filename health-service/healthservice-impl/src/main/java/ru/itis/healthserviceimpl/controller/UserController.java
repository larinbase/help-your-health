package ru.itis.healthserviceimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.healthserviceapi.api.UserApi;
import ru.itis.healthserviceapi.dto.request.UserSave;
import ru.itis.healthserviceapi.dto.request.UserUpdate;
import ru.itis.healthserviceapi.dto.response.UserResponse;
import ru.itis.healthserviceimpl.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService service;

    @Override
    public void create(UserSave userSave) {
        service.create(userSave);
    }

    @Override
    @PreAuthorize("@CommunityRoleService.hasAnyRole(@CommunityRoleType.MODERATOR) || " +
            "@UserCheckService.hasUserName(#username)")
    public UserResponse findByUsername(String username) {
        return service.findByUsername(username);
    }

    @Override
    @PreAuthorize("@CommunityRoleService.hasAnyRole(@CommunityRoleType.USER)")
    public void update(UserUpdate userUpdate) {
        service.update(userUpdate);
    }

    @Override
    @PreAuthorize("@CommunityRoleService.hasAnyRole(@CommunityRoleType.MODERATOR) || " +
            "@UserCheckService.hasUserId(#id)")
    public void deleteById(UUID id) {
        service.deleteById(id);
    }
}
