package ru.itis.healthserviceimpl.service;

import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.healthserviceapi.dto.request.DrinkingWaterRequest;
import ru.itis.healthserviceapi.dto.response.DrinkingWaterResponse;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface DrinkingWaterService {

    DrinkingWaterResponse findDrinkingWaterById(@PathVariable UUID id);
    
    List<DrinkingWaterResponse> findDrinkingByDate(String date);

    DrinkingWaterResponse findLastDrinkingWaterByUser(@PathVariable UUID userId);

    List<DrinkingWaterResponse> findAllDrinkingWaterByUser(@PathVariable UUID userId);

    DrinkingWaterResponse save(DrinkingWaterRequest request);

    void delete(UUID id);

    List<DrinkingWaterResponse> findAllDrinkingWaterByTimePeriod(UUID userId, Instant from, Instant to);
}
