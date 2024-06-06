package ru.itis.healthauthimpl.service;

import ru.itis.healthauthapi.dto.UserResponse;

public interface UserService {

    UserResponse findByUsername(String username);
}
