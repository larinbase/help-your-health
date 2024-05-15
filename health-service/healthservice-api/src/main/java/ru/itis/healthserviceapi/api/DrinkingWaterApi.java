package ru.itis.healthserviceapi.api;

import org.springframework.web.bind.annotation.*;
import ru.itis.healthserviceapi.dto.request.DrinkingWaterRequest;
import ru.itis.healthserviceapi.dto.response.DrinkingWaterResponse;

import java.util.List;
import java.util.UUID;


@RequestMapping("api/v1/drinking-water")
public interface DrinkingWaterApi {

    @GetMapping("/{id}")
    DrinkingWaterResponse getDrinkingWaterById(@PathVariable UUID id);

    @GetMapping("/user/{userId}/last")
    DrinkingWaterResponse getLastDrinkingWaterByUser(@PathVariable UUID userId);

    @GetMapping("/user/{userId}/all")
    List<DrinkingWaterResponse> getAllDrinkingWaterByUser(@PathVariable UUID userId);

    @PostMapping
    void saveDrinkingWater(DrinkingWaterRequest request);

    @DeleteMapping
    void deleteDrinkingWater(UUID id);


}
