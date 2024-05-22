package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.UserSave;
import ru.itis.healthserviceapi.dto.request.UserUpdate;
import ru.itis.healthserviceapi.dto.response.UserResponse;
import ru.itis.healthserviceimpl.exception.CommunityRoleNotFoundException;
import ru.itis.healthserviceimpl.exception.UserAlreadyExistException;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.mapper.UserMapper;
import ru.itis.healthserviceimpl.model.CommunityRole;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roles.CommunityRoleType;
import ru.itis.healthserviceimpl.repository.CommunityRoleRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final CommunityRoleRepository communityRoleRepository;

    @Override
    public void create(UserSave userSave) {
        if (userRepository.findByUsername(userSave.username()).isPresent()) {
            throw new UserAlreadyExistException(userSave.username()); // ToDo: Custom exception
        }
        CommunityRoleType roleType = CommunityRoleType.valueOf(userSave.role());
        CommunityRole role = communityRoleRepository.findByType(roleType)
                .orElseThrow(()-> new CommunityRoleNotFoundException(roleType.name()));
        User user = mapper.fromRequest(userSave);
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public UserResponse findByUsername(String username) {
        return mapper.toResponse(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException(username))
        );
    }

    @Override
    public void update(UserUpdate userUpdate, UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setFirstname(userUpdate.firstname());
        user.setLastname(userUpdate.lastname());
        user.setAge(userUpdate.age());
        user.setHeight(userUpdate.height());
        user.setWeight(user.getWeight());
        userRepository.save(user);
    }

    @Override
    public void deleteById(UUID id) {
        if (userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
