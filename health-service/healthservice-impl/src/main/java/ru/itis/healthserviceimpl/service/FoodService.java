package ru.itis.healthserviceimpl.service;

import ru.itis.healthserviceapi.dto.request.FoodRequest;
import ru.itis.healthserviceapi.dto.response.FoodResponse;

import java.util.Set;
import java.util.UUID;

public interface FoodService {

    UUID save(FoodRequest foodRequest);

    FoodResponse getById(UUID id);

    Set<FoodResponse> getAll();

    void deleteById(UUID id);

    void putById(UUID id, FoodRequest foodRequest);

}
