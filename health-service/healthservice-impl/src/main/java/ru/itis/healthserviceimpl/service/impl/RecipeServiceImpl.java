package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.RecipeRequest;
import ru.itis.healthserviceapi.dto.response.RecipeResponse;
import ru.itis.healthserviceimpl.exception.RecipeNotFoundException;
import ru.itis.healthserviceimpl.mapper.RecipeMapper;
import ru.itis.healthserviceimpl.model.MyPageImpl;
import ru.itis.healthserviceimpl.model.Recipe;
import ru.itis.healthserviceimpl.repository.RecipeRepository;
import ru.itis.healthserviceimpl.service.RecipeService;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository repository;

    private final RecipeMapper mapper;

    @Override
    @Cacheable(value = "recipes")
    public RecipeResponse create(RecipeRequest request) {
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    @Cacheable(value = "recipes")
    public Page<RecipeResponse> findAll(int offset, int limit) {
        return new MyPageImpl<>(mapper.toResponse(repository.findAll(PageRequest.of(offset, limit,
                Sort.by(Sort.Direction.ASC, "title")))));
    }

    @Override
    @Cacheable(value = "recipes", key = "#id")
    public RecipeResponse findById(ObjectId id) {
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id)));
    }

    @Override
    @Cacheable(value = "recipes", key = "#title")
    public Page<RecipeResponse> findByTitle(String title, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(repository.findAllByTitleRegex(title, pageable));
    }

    @Override
    @Cacheable(value = "recipes", key = "#category")
    public Page<RecipeResponse> findByCategory(String category, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(repository.findAllByCategoriesContaining(category, pageable));
    }

    @Override
    @Cacheable(value = "recipes", key = "#cookingTime")
    public Page<RecipeResponse> findByCookingTime(int cookingTime, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(repository.findAllByCookingTime(cookingTime, pageable));
    }

    @Override
    @CachePut(value = "recipes", key = "#id")
    public RecipeResponse update(ObjectId id, RecipeRequest request) {
        repository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id));
        Recipe recipe = mapper.toEntity(request);
        recipe.setId(id);
        return mapper.toResponse(repository.save(recipe));
    }

    @Override
    @CacheEvict(value = "recipes", key = "#id")
    public void deleteById(ObjectId id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id)));
    }
}
