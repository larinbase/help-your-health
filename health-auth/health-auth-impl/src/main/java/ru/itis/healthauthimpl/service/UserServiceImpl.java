package ru.itis.healthauthimpl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.healthauthapi.dto.UserResponse;
import ru.itis.healthauthimpl.mapper.UserMapper;
import ru.itis.healthauthimpl.repository.UserRepository;

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
