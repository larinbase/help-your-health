package ru.itis.healthserviceimpl.controller;

import lombok.RequiredArgsConstructor;
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
    public UserResponse findByUsername(String username) {
        return service.findByUsername(username);
    }

    @Override
    public void update(UserUpdate userUpdate) { // ToDO: получать id пользователя, который шлет запрос через UserDetails
        service.update(userUpdate, UUID.fromString("f8afcc99-ffd0-454e-b679-944d5ff5c7d5"));
    }

    @Override
    public void deleteById() { // ToDO: получать id пользователя, который шлет запрос через UserDetails
        service.deleteById(UUID.fromString("f8afcc99-ffd0-454e-b679-944d5ff5c7d5"));
    }
}
