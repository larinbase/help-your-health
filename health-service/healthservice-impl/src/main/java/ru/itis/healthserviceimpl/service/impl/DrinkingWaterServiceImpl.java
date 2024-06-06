package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.DrinkingWaterRequest;
import ru.itis.healthserviceapi.dto.response.DrinkingWaterResponse;
import ru.itis.healthserviceimpl.exception.DrinkingWaterNotFoundServiceException;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.mapper.DrinkingWaterMapper;
import ru.itis.healthserviceimpl.model.DrinkingWater;
import ru.itis.healthserviceimpl.repository.DrinkingWaterRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.service.DrinkingWaterRoleService;
import ru.itis.healthserviceimpl.service.DrinkingWaterService;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DrinkingWaterServiceImpl implements DrinkingWaterService {

    private final DrinkingWaterRepository drinkingWaterRepository;
    private final UserRepository userRepository;
    private final DrinkingWaterMapper drinkingWaterMapper;
    private final DrinkingWaterRoleService roleService;

    @Override
    @Cacheable(value = "water", key = "#id")
    public DrinkingWaterResponse findDrinkingWaterById(UUID id) {
        return drinkingWaterRepository.findById(id)
                .map(drinkingWaterMapper::toResponse)
                .orElseThrow(() -> new DrinkingWaterNotFoundServiceException(id));
    }

    @Override
    @Cacheable(value = "water", key = "#userId")
    public DrinkingWaterResponse findLastDrinkingWaterByUser(UUID userId) {
        return drinkingWaterRepository.findLastDrinkingWaterByUserId(userId)
                .map(drinkingWaterMapper::toResponse)
                .orElseThrow(() -> new DrinkingWaterNotFoundServiceException(userId));
    }

    @Override
    @Cacheable(value = "water", key = "#userId")
    public List<DrinkingWaterResponse> findAllDrinkingWaterByUser(UUID userId) {
        return drinkingWaterRepository.findAllByUserId(userId)
                .stream()
                .map(drinkingWaterMapper::toResponse).toList();
    }

    @Override
    @Cacheable(value = "water")
    public DrinkingWaterResponse save(DrinkingWaterRequest request) {
        DrinkingWater drinkingWater = drinkingWaterMapper.toEntity(request);
        drinkingWater.setUser(
                userRepository.findById(request.accountId())
                        .orElseThrow(() -> new UserNotFoundException(request.accountId()))
        );
        drinkingWater = drinkingWaterRepository.save(drinkingWater);
        roleService.create(drinkingWater.getUser().getId(), drinkingWater.getId());
        return drinkingWaterMapper.toResponse(drinkingWater);
    }

    @Override
    @CacheEvict(value = "water", key = "#id")
    public void delete(UUID id) {
        drinkingWaterRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "water", key = "#userId")
    public List<DrinkingWaterResponse> findAllDrinkingWaterByTimePeriod(UUID userId, Instant from, Instant to) {
        return drinkingWaterRepository.findAllByUserIdAndCreateDateBetween(userId, from, to)
                .stream().map(drinkingWaterMapper::toResponse).toList();
    }
}
