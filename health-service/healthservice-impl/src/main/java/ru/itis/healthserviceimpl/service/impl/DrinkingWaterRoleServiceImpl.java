package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.NotFoundException;
import ru.itis.healthserviceimpl.exception.ServiceException;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.model.*;
import ru.itis.healthserviceimpl.model.roles.DrinkingWaterRoleType;
import ru.itis.healthserviceimpl.repository.*;
import ru.itis.healthserviceimpl.service.DrinkingWaterRoleService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DrinkingWaterRoleServiceImpl implements DrinkingWaterRoleService {

    private final DrinkingWaterRoleRepository roleRepository;
    private final DrinkingWaterRepository drinkingWaterRepository;
    private final UserRepository userRepository;

    @Override
    public void create(UUID userId, UUID drinkingWaterId) {
        if (!roleRepository.findByDrinkingWaterIdAndUserId(drinkingWaterId, userId).isEmpty()) {
            String message = "Role for user %s in drinking water %s already exist";
            throw new ServiceException(message.formatted(userId, drinkingWaterId), HttpStatus.CONFLICT);
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        DrinkingWater drinkingWater = drinkingWaterRepository.findById(drinkingWaterId)
                .orElseThrow(() -> new NotFoundException("Exercise %s not found".formatted(drinkingWaterId)));
        DrinkingWaterRole drinkingWaterRole = DrinkingWaterRole.builder()
                .drinkingWater(drinkingWater)
                .user(user)
                .type(DrinkingWaterRoleType.EDITOR)
                .build();
        roleRepository.save(drinkingWaterRole);
    }
}
