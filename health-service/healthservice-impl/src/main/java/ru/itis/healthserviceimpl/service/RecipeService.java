package ru.itis.healthserviceimpl.service;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import ru.itis.healthserviceapi.dto.request.RecipeRequest;
import ru.itis.healthserviceapi.dto.response.RecipeResponse;

public interface RecipeService {

    void create(RecipeRequest request);

    Page<RecipeResponse> findAll(int offset, int limit);

    RecipeResponse findById(ObjectId id);

    Page<RecipeResponse> findByTitle(String title, int offset, int limit);

    Page<RecipeResponse> findByCategory(String category, int offset, int limit);

    Page<RecipeResponse> findByCookingTime(int cookingTime, int offset, int limit);

    void update(ObjectId id, RecipeRequest request);

    void deleteById(ObjectId id);
}
