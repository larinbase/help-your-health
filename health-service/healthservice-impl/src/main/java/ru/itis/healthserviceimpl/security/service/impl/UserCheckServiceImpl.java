package ru.itis.healthserviceimpl.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.security.service.UserCheckService;

import java.util.UUID;

@Service("UserCheckService")
@RequiredArgsConstructor
public class UserCheckServiceImpl implements UserCheckService {

    private final UserRepository repository;

    @Override
    public boolean hasUserId(UUID id) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = repository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return user.getId().equals(id);
    }

    @Override
    public boolean hasUserName(String username) {
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.equals(username);
    }
}
