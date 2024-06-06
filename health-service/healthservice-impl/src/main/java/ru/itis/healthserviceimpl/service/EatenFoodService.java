package ru.itis.healthserviceimpl.service;

import ru.itis.healthserviceapi.dto.request.EatenFoodRequest;
import ru.itis.healthserviceapi.dto.response.EatenFoodResponse;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface EatenFoodService {

    UUID save(EatenFoodRequest eatenFoodRequest);

    EatenFoodResponse getById(UUID id);
    
    List<EatenFoodResponse> getByDate(String date);

    Set<EatenFoodResponse> getAll();

    void deleteById(UUID id);

    void putById(UUID id, EatenFoodRequest eatenFoodRequest);

}
