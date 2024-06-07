package ru.itis.healthserviceimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("@CommunityRoleService.hasAnyRole(@FoodCategoryRoleType.EDITOR)")
    public UUID save(FoodCategoryRequest foodCategoryRequest) {
        return service.save(foodCategoryRequest);
    }

    @Override
    @PreAuthorize("@CommunityRoleService.hasAnyRole(@FoodCategoryRoleType.VIEWER)")
    public FoodCategoryResponse getById(UUID id) {
        return service.getById(id);
    }

    @Override
    @PreAuthorize("@CommunityRoleService.hasAnyRole(@FoodCategoryRoleType.VIEWER)")
    public Set<FoodCategoryResponse> getAll() {
        return service.getAll();
    }

    @Override
    @PreAuthorize("@CommunityRoleService.hasAnyRole(@FoodCategoryRoleType.EDITOR)")
    public void deleteById(UUID id) {
        service.deleteById(id);
    }

    @Override
    @PreAuthorize("@CommunityRoleService.hasAnyRole(@FoodCategoryRoleType.EDITOR)")
    public void putById(UUID id, FoodCategoryRequest foodCategoryRequest) {
        service.putById(id, foodCategoryRequest);
    }
}
