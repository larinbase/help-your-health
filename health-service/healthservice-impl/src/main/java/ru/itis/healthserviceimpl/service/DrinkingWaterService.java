package ru.itis.healthserviceimpl.service;

import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.healthserviceapi.dto.request.DrinkingWaterRequest;
import ru.itis.healthserviceapi.dto.response.DrinkingWaterResponse;

import java.util.List;
import java.util.UUID;

public interface DrinkingWaterService {

    DrinkingWaterResponse findDrinkingWaterById(@PathVariable UUID id);

    DrinkingWaterResponse findLastDrinkingWaterByUser(@PathVariable UUID userId);

    List<DrinkingWaterResponse> findAllDrinkingWaterByUser(@PathVariable UUID userId);

    void save(DrinkingWaterRequest request);

    void delete(UUID id);
}
