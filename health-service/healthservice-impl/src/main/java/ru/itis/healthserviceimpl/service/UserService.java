package ru.itis.healthserviceimpl.service;

import ru.itis.healthserviceapi.dto.request.UserSave;
import ru.itis.healthserviceapi.dto.request.UserUpdate;
import ru.itis.healthserviceapi.dto.response.UserResponse;

import java.util.UUID;

public interface UserService {
    void create(UserSave userSave);

    UserResponse findByUsername(String username);

    void update(UserUpdate userUpdate, UUID id);

    void deleteById(UUID id);
}
