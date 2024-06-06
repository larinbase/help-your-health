package ru.itis.healthserviceimpl.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.healthserviceapi.api.FoodApi;
import ru.itis.healthserviceapi.dto.request.FoodRequest;
import ru.itis.healthserviceapi.dto.response.FoodResponse;
import ru.itis.healthserviceimpl.service.FoodService;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FoodController implements FoodApi {

    private final FoodService service;

    @Override
    @PreAuthorize("@CommunityRoleService.hasAnyRole(@FoodRoleType.EDITOR)")
    public UUID save(FoodRequest foodRequest) {
        return service.save(foodRequest);
    }

    @Override
    @PreAuthorize("@CommunityRoleService.hasAnyRole(@FoodRoleType.VIEWER)")
    public FoodResponse getById(UUID id) {
        return service.getById(id);
    }

    @Override
    @PreAuthorize("@CommunityRoleService.hasAnyRole(@FoodRoleType.VIEWER)")
    public Set<FoodResponse> getAll() {
        return service.getAll();
    }

    @Override
    @PreAuthorize("@CommunityRoleService.hasAnyRole(@FoodRoleType.EDITOR)")
    public void deleteById(UUID id) {
        service.deleteById(id);
    }

    @Override
    @PreAuthorize("@CommunityRoleService.hasAnyRole(@FoodRoleType.EDITOR)")
    public void putById(UUID id, FoodRequest foodRequest) {
        service.putById(id, foodRequest);
    }
}
