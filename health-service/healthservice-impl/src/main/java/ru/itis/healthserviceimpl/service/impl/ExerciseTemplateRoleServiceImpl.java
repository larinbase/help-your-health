package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.NotFoundException;
import ru.itis.healthserviceimpl.exception.ServiceException;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.model.ExerciseTemplateEntity;
import ru.itis.healthserviceimpl.model.role.ExerciseTemplateRole;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roletype.ExerciseTemplateRoleType;
import ru.itis.healthserviceimpl.repository.ExerciseTemplateRepository;
import ru.itis.healthserviceimpl.repository.ExerciseTemplateRoleRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.service.ExerciseTemplateRoleService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExerciseTemplateRoleServiceImpl implements ExerciseTemplateRoleService {

    private final UserRepository userRepository;
    private final ExerciseTemplateRoleRepository roleRepository;
    private final ExerciseTemplateRepository exerciseTemplateRepository;

    @Override
    public void create(UUID userId, UUID exerciseTemplateEntityId) {
        if (!roleRepository.findByExerciseTemplateEntityIdAndUserId(exerciseTemplateEntityId, userId).isEmpty()) {
            String message = "Role for user %s in exercise template %s already exist";
            throw new ServiceException(message.formatted(userId, exerciseTemplateEntityId), HttpStatus.CONFLICT);
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        ExerciseTemplateEntity exerciseTemplateEntity = exerciseTemplateRepository.findById(exerciseTemplateEntityId)
                .orElseThrow(() -> new NotFoundException(
                        "Exercise template %s not found".formatted(exerciseTemplateEntityId))
                );
        ExerciseTemplateRole exerciseTemplateRole = ExerciseTemplateRole.builder()
                .exerciseTemplateEntity(exerciseTemplateEntity)
                .user(user)
                .type(ExerciseTemplateRoleType.EDITOR)
                .build();
        roleRepository.save(exerciseTemplateRole);
    }
}
