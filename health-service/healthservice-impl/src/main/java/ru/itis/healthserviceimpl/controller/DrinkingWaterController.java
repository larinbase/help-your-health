package ru.itis.healthserviceimpl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.healthserviceapi.api.DrinkingWaterApi;
import ru.itis.healthserviceapi.dto.request.DrinkingWaterRequest;
import ru.itis.healthserviceapi.dto.response.DrinkingWaterResponse;
import ru.itis.healthserviceimpl.service.DrinkingWaterService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DrinkingWaterController implements DrinkingWaterApi {

    private final DrinkingWaterService drinkingWaterService;

    @Override
    public DrinkingWaterResponse getDrinkingWaterById(UUID id) {
        return null;
    }

    @Override
    public DrinkingWaterResponse getLastDrinkingWaterByUser(UUID userId) {
        return null;
    }

    @Override
    public List<DrinkingWaterResponse> getAllDrinkingWaterByUser(UUID userId) {
        return null;
    }

    @Override
    public void saveDrinkingWater(DrinkingWaterRequest request) {

    }

    @Override
    public void deleteDrinkingWater(UUID id) {

    }
}
