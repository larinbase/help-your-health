package ru.itis.healthserviceimpl.service;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import ru.itis.healthserviceapi.dto.request.RecipeRequest;
import ru.itis.healthserviceapi.dto.response.RecipeResponse;

import java.util.UUID;

public interface RecipeService {

    RecipeResponse create(RecipeRequest request);

    Page<RecipeResponse> findAll(int offset, int limit);

    RecipeResponse findById(UUID id);

    Page<RecipeResponse> findByTitle(String title, int offset, int limit);

    Page<RecipeResponse> findByCategory(String category, int offset, int limit);

    Page<RecipeResponse> findByCookingTime(int cookingTime, int offset, int limit);

    RecipeResponse update(UUID id, RecipeRequest request);

    void deleteById(UUID id);
}
