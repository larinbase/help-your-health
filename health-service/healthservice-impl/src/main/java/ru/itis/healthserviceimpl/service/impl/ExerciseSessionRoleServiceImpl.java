package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.NotFoundException;
import ru.itis.healthserviceimpl.exception.ServiceException;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.model.*;
import ru.itis.healthserviceimpl.model.roles.ExerciseSessionRoleType;
import ru.itis.healthserviceimpl.repository.*;
import ru.itis.healthserviceimpl.service.ExerciseSessionRoleService;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ExerciseSessionRoleServiceImpl implements ExerciseSessionRoleService {

    private final UserRepository userRepository;
    private final ExerciseSessionRepository exerciseSessionRepository;
    private final ExerciseSessionRoleRepository roleRepository;

    @Override
    public void create(UUID userId, UUID exerciseSessionId) {
        if (!roleRepository.findByExerciseSessionEntityIdAndUserId(exerciseSessionId, userId).isEmpty()) {
            String message = "Role for user %s in exercise session %s already exist";
            throw new ServiceException(message.formatted(userId, exerciseSessionId), HttpStatus.CONFLICT);
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        ExerciseSessionEntity exerciseTemplateEntity = exerciseSessionRepository.findById(exerciseSessionId)
                .orElseThrow(() -> new NotFoundException("Exercise %s not found".formatted(exerciseSessionId)));
        ExerciseSessionRole exerciseTemplateRole = ExerciseSessionRole.builder()
                .exerciseSessionEntity(exerciseTemplateEntity)
                .user(user)
                .type(ExerciseSessionRoleType.EDITOR)
                .build();
        roleRepository.save(exerciseTemplateRole);
    }
}
