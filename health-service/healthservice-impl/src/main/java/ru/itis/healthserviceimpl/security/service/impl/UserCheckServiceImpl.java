package ru.itis.healthserviceimpl.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.security.service.UserCheckService;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;

import java.util.UUID;

@Service("UserCheckService")
public class UserCheckServiceImpl implements UserCheckService {
    @Override
    public boolean hasUserId(UUID id) {
        BaseUserDetails principal = (BaseUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getId().equals(id);
    }

    @Override
    public boolean hasUserName(String username) {
        BaseUserDetails principal = (BaseUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getUsername().equals(username);
    }
}
