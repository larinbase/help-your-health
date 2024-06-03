package ru.itis.healthserviceimpl.service;

import ru.itis.healthserviceapi.dto.request.UserSave;
import ru.itis.healthserviceapi.dto.request.UserUpdate;
import ru.itis.healthserviceapi.dto.response.UserResponse;

import java.util.UUID;

public interface UserService {
    UserResponse create(UserSave userSave);

    UserResponse findByUsername(String username);

    UserResponse update(UserUpdate userUpdate);

    void deleteById(UUID id);
}
