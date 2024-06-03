package ru.itis.healthserviceimpl.service;

import ru.itis.healthserviceapi.dto.request.FoodCategoryRequest;
import ru.itis.healthserviceapi.dto.response.FoodCategoryResponse;

import java.util.Set;
import java.util.UUID;

public interface FoodCategoryService {

    UUID save(FoodCategoryRequest foodCategoryRequest);

    FoodCategoryResponse getById(UUID id);

    Set<FoodCategoryResponse> getAll();

    void deleteById(UUID id);

    void putById(UUID id, FoodCategoryRequest foodCategoryRequest);

}
