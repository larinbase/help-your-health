package ru.itis.healthauthimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.healthauthapi.dto.UserResponse;
import ru.itis.healthauthimpl.mapper.UserMapper;
import ru.itis.healthauthimpl.repository.UserRepository;
import ru.itis.healthauthimpl.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse findByUsername(String username) {
        return userMapper.toUserResponse(userRepository.findByUsername(username));
    }
}
