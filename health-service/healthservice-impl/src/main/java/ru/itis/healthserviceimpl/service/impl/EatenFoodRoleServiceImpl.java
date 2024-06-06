package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.NotFoundException;
import ru.itis.healthserviceimpl.exception.ServiceException;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.model.*;
import ru.itis.healthserviceimpl.model.roles.EatenFoodRoleType;
import ru.itis.healthserviceimpl.model.roles.ExerciseTemplateRoleType;
import ru.itis.healthserviceimpl.repository.EatenFoodRepository;
import ru.itis.healthserviceimpl.repository.EatenFoodRoleRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.service.EatenFoodRoleService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EatenFoodRoleServiceImpl implements EatenFoodRoleService {

    private final EatenFoodRepository eatenFoodRepository;
    private final EatenFoodRoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public void create(UUID userId, UUID eatenFoodId) {
        if (!roleRepository.findByEatenFoodIdAndUserId(eatenFoodId, userId).isEmpty()) {
            String message = "Role for user %s in eaten food %s already exist";
            throw new ServiceException(message.formatted(userId, eatenFoodId), HttpStatus.CONFLICT);
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        EatenFood eatenFood = eatenFoodRepository.findById(eatenFoodId)
                .orElseThrow(() -> new NotFoundException(
                        "Eaten food %s not found".formatted(eatenFoodId))
                );
        EatenFoodRole eatenFoodRole = EatenFoodRole.builder()
                .eatenFood(eatenFood)
                .user(user)
                .type(EatenFoodRoleType.EDITOR)
                .build();
        roleRepository.save(eatenFoodRole);
    }
}
