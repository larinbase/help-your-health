package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.DrinkingWaterRequest;
import ru.itis.healthserviceapi.dto.response.DrinkingWaterResponse;
import ru.itis.healthserviceimpl.exception.DrinkingWaterNotFoundeServiceException;
import ru.itis.healthserviceimpl.mapper.DrinkingWaterMapper;
import ru.itis.healthserviceimpl.repository.DrinkingWaterRepository;
import ru.itis.healthserviceimpl.service.DrinkingWaterService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DrinkingWaterServiceImpl implements DrinkingWaterService {

    private final DrinkingWaterRepository drinkingWaterRepository;

    private final DrinkingWaterMapper drinkingWaterMapper;

    @Override
    public DrinkingWaterResponse findDrinkingWaterById(UUID id) {
        return drinkingWaterRepository.findById(id)
                .map(drinkingWaterMapper::toResponse)
                .orElseThrow(() -> new DrinkingWaterNotFoundeServiceException(id));
    }

    @Override
    public DrinkingWaterResponse findLastDrinkingWaterByUser(UUID userId) {
        return drinkingWaterRepository.findLastDrinkingWaterByUserId(userId)
                .map(drinkingWaterMapper::toResponse)
                .orElseThrow(() -> new DrinkingWaterNotFoundeServiceException(userId));
    }

    @Override
    public List<DrinkingWaterResponse> findAllDrinkingWaterByUser(UUID userId) {
        return drinkingWaterRepository.findAllByUserId(userId)
                .stream()
                .map(drinkingWaterMapper::toResponse).toList();
    }

    @Override
    public void save(DrinkingWaterRequest request) {
        drinkingWaterRepository.save(drinkingWaterMapper.toEntity(request));
    }

    @Override
    public void delete(UUID id) {
        drinkingWaterRepository.deleteById(id);
    }
}
