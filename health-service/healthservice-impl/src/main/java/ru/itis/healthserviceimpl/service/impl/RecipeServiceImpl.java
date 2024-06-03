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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.RecipeRequest;
import ru.itis.healthserviceapi.dto.response.RecipeResponse;
import ru.itis.healthserviceimpl.exception.RecipeNotFoundException;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.mapper.IngredientMapper;
import ru.itis.healthserviceimpl.mapper.NutritionalInfoMapper;
import ru.itis.healthserviceimpl.mapper.RecipeMapper;
import ru.itis.healthserviceimpl.model.MyPageImpl;
import ru.itis.healthserviceimpl.model.Recipe;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.repository.RecipeRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.service.RecipeRoleService;
import ru.itis.healthserviceimpl.service.RecipeService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final RecipeMapper mapper;
    private final RecipeRoleService recipeRoleService;
    private final NutritionalInfoMapper nutritionalInfoMapper;
    private final IngredientMapper ingredientMapper;

    @Override
    @Cacheable(value = "recipes")
    public RecipeResponse create(RecipeRequest request) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        Recipe recipe = mapper.toEntity(request);
        recipe.setAuthor(user.getId());
        recipe.setId(UUID.randomUUID());
        recipe = recipeRepository.save(recipe);
        recipeRoleService.create(user.getId(), recipe.getId());
        return mapper.toResponse(recipe);
    }

    @Override
    @Cacheable(value = "recipes")
    public Page<RecipeResponse> findAll(int offset, int limit) {
        return new MyPageImpl<>(mapper.toResponse(recipeRepository.findAll(PageRequest.of(offset, limit,
                Sort.by(Sort.Direction.ASC, "title")))));
    }

    @Override
    @Cacheable(value = "recipes", key = "#id")
    public RecipeResponse findById(UUID id) {
        return mapper.toResponse(recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id)));
    }

    @Override
    @Cacheable(value = "recipes", key = "#title")
    public Page<RecipeResponse> findByTitle(String title, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(recipeRepository.findAllByTitleRegex(title, pageable));
    }

    @Override
    @Cacheable(value = "recipes", key = "#category")
    public Page<RecipeResponse> findByCategory(String category, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(recipeRepository.findAllByCategoriesContaining(category, pageable));
    }

    @Override
    @Cacheable(value = "recipes", key = "#cookingTime")
    public Page<RecipeResponse> findByCookingTime(int cookingTime, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return mapper.toResponse(recipeRepository.findAllByCookingTime(cookingTime, pageable));
    }

    @Override
    @CachePut(value = "recipes", key = "#id")
    public RecipeResponse update(UUID id, RecipeRequest request) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id));
        recipe.setTitle(request.title());
        recipe.setCategories(request.categories());
        recipe.setCookingTime(request.cookingTime());
        recipe.setIngredients(ingredientMapper.toEntity(request.ingredients()));
        recipe.setInstructions(request.instructions());
        recipe.setImages(request.images());
        recipe.setNutritionalInfo(nutritionalInfoMapper.toEntity(request.nutritionalInfo()));
        recipe.setId(id);
        return mapper.toResponse(recipeRepository.save(recipe));
    }

    @Override
    @CacheEvict(value = "recipes", key = "#id")
    public void deleteById(UUID id) {
        recipeRepository.delete(recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id)));
    }
}
