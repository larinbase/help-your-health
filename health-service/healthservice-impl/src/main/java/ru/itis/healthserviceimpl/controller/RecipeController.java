package ru.itis.healthserviceimpl.controller;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.healthserviceapi.api.RecipeApi;
import ru.itis.healthserviceapi.dto.request.RecipeRequest;
import ru.itis.healthserviceapi.dto.response.RecipeResponse;
import ru.itis.healthserviceimpl.service.RecipeService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RecipeController implements RecipeApi {

    private final RecipeService service;

    @Override
    public void create(RecipeRequest request) {
        service.create(request);
    }

    @Override
    public Page<RecipeResponse> findAll(int offset, int limit) {
        return service.findAll(offset, limit);
    }

    @Override
    public RecipeResponse findById(UUID id) {
        return service.findById(id);
    }

    @Override
    public Page<RecipeResponse> findByTitle(String title, int offset, int limit) {
        return service.findByTitle(title, offset, limit);
    }

    @Override
    public Page<RecipeResponse> findByCategory(String category, int offset, int limit) {
        return service.findByCategory(category, offset, limit);
    }

    @Override
    public Page<RecipeResponse> findByCookingTime(int category, int offset, int limit) {
        return service.findByCookingTime(category, offset, limit);
    }

    @Override
    @PreAuthorize("@RecipeRoleService.hasAnyRoleByRecipeId(#id, @RecipeRoleType.EDITOR)")
    public void update(UUID id, RecipeRequest request) {
        service.update(id, request);
    }

    @Override
    public void deleteById(UUID id) {
        service.deleteById(id);
    }
}
