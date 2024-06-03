package ru.itis.healthserviceimpl.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.healthserviceapi.api.EatenFoodApi;
import ru.itis.healthserviceapi.dto.request.EatenFoodRequest;
import ru.itis.healthserviceapi.dto.response.EatenFoodResponse;
import ru.itis.healthserviceimpl.service.EatenFoodService;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EatenFoodController implements EatenFoodApi {

    private final EatenFoodService service;

    @Override
    public UUID save(EatenFoodRequest eatenFoodRequest) {
        return service.save(eatenFoodRequest);
    }

    @Override
    public EatenFoodResponse getById(UUID id) {
        return service.getById(id);
    }

    @Override
    public Set<EatenFoodResponse> getAll() {
        return service.getAll();
    }

    @Override
    public void deleteById(UUID id) {
        service.deleteById(id);
    }

    @Override
    public void putById(UUID id, EatenFoodRequest eatenFoodRequest) {
        service.putById(id, eatenFoodRequest);
    }
}
