package ru.itis.healthserviceimpl.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.healthserviceapi.api.FoodCategoryApi;
import ru.itis.healthserviceapi.dto.request.FoodCategoryRequest;
import ru.itis.healthserviceapi.dto.response.FoodCategoryResponse;
import ru.itis.healthserviceimpl.service.FoodCategoryService;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FoodCategoryController implements FoodCategoryApi {

    private final FoodCategoryService service;


    @Override
    public UUID save(FoodCategoryRequest foodCategoryRequest) {
        return service.save(foodCategoryRequest);
    }

    @Override
    public FoodCategoryResponse getById(UUID id) {
        return service.getById(id);
    }

    @Override
    public Set<FoodCategoryResponse> getAll() {
        return service.getAll();
    }

    @Override
    public void deleteById(UUID id) {
        service.deleteById(id);
    }

    @Override
    public void putById(UUID id, FoodCategoryRequest foodCategoryRequest) {
        service.putById(id, foodCategoryRequest);
    }
}
