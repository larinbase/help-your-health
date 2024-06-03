package ru.itis.healthserviceimpl.security.service;

import java.util.UUID;

public interface UserCheckService {
    boolean hasUserId(UUID id);

    boolean hasUserName(String username);
}
