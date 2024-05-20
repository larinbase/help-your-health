package ru.itis.healthserviceimpl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.healthserviceapi.api.DrinkingWaterApi;
import ru.itis.healthserviceapi.dto.request.DrinkingWaterRequest;
import ru.itis.healthserviceapi.dto.response.DrinkingWaterResponse;
import ru.itis.healthserviceimpl.service.DrinkingWaterService;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DrinkingWaterController implements DrinkingWaterApi {

    private final DrinkingWaterService drinkingWaterService;

    @Override
    public DrinkingWaterResponse getDrinkingWaterById(UUID id) {
        return drinkingWaterService.findDrinkingWaterById(id);
    }

    @Override
    public DrinkingWaterResponse getLastDrinkingWaterByUser(UUID userId) {
        return drinkingWaterService.findLastDrinkingWaterByUser(userId);
    }

    @Override
    public List<DrinkingWaterResponse> getAllDrinkingWaterByUser(UUID userId) {
        return drinkingWaterService.findAllDrinkingWaterByUser(userId);
    }

    @Override
    public void saveDrinkingWater(DrinkingWaterRequest request) {
        drinkingWaterService.save(request);
    }

    @Override
    public void deleteDrinkingWater(UUID id) {
        drinkingWaterService.delete(id);
    }

    @Override
    public List<DrinkingWaterResponse> getDrinkingWaterForTimePeriod(UUID userId, Instant from, Instant to) {
        return drinkingWaterService.findAllDrinkingWaterByTimePeriod(userId, from, to);
    }
}
