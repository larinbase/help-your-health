package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.EatenFoodRequest;
import ru.itis.healthserviceapi.dto.response.EatenFoodResponse;
import ru.itis.healthserviceimpl.exception.EatenFoodNotFoundException;
import ru.itis.healthserviceimpl.exception.FoodNotFoundException;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.mapper.EatenFoodMapper;
import ru.itis.healthserviceimpl.model.EatenFood;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.repository.EatenFoodRepository;
import ru.itis.healthserviceimpl.repository.FoodRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;
import ru.itis.healthserviceimpl.service.EatenFoodRoleService;
import ru.itis.healthserviceimpl.exception.RecipeNotFoundException;
import ru.itis.healthserviceimpl.model.Recipe;
import ru.itis.healthserviceimpl.repository.RecipeRepository;
import ru.itis.healthserviceimpl.service.EatenFoodService;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EatenFoodServiceImpl implements EatenFoodService {

    private final EatenFoodRepository eatenFoodRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final EatenFoodMapper mapper;
    private final EatenFoodRoleService eatenFoodRoleService;

    @Override
    public UUID save(EatenFoodRequest eatenFoodRequest) {
        EatenFood eatenFood = mapper.toEntity(eatenFoodRequest);
        UUID foodId = eatenFoodRequest.foodId();
        BaseUserDetails principal = (BaseUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userRepository.findById(principal.getId())
                .orElseThrow(() -> new UserNotFoundException(principal.getId()));
        if (foodId != null) {
            eatenFood.setFood(foodRepository.findById(foodId)
                    .orElseThrow(() -> new FoodNotFoundException(foodId)));
        }
        eatenFood.setUser(user);
        eatenFood = eatenFoodRepository.save(eatenFood);
        eatenFoodRoleService.create(user.getId(), eatenFood.getId());
        return eatenFood.getId();
    }

    @Override
    public EatenFoodResponse getById(UUID id) {
        EatenFood eatenFood = eatenFoodRepository.findById(id)
                .orElseThrow(() -> new EatenFoodNotFoundException(id));
        Recipe recipe = null;
        if(eatenFood.getRecipeId() != null){
            recipe = recipeRepository.findById(eatenFood.getRecipeId())
                        .orElseThrow(() -> new RecipeNotFoundException(eatenFood.getRecipeId()));
        }
        return mapper.toResponse(
             eatenFood,
             recipe
        );
    }

    @Override
    public List<EatenFoodResponse> getByDate(String date) {
        BaseUserDetails user = (BaseUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Date sqlDate = Date.valueOf(date);
        LocalDate localDate = sqlDate.toLocalDate();
        Instant startOfDay = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant endOfDay = startOfDay.plus(1, ChronoUnit.DAYS).minus(1, ChronoUnit.MILLIS);
        return eatenFoodRepository
                        .findAllByUserIdAndCreateDateBetween(user.getId(), startOfDay, endOfDay)
                        .stream().map(e -> {
                            Recipe recipe = null;
                            if(e.getRecipeId() != null){
                                recipe = recipeRepository.findById(e.getRecipeId())
                                            .orElseThrow(() -> new RecipeNotFoundException(e.getRecipeId()));
                            }
                            return mapper.toResponse(
                                    e, recipe
                            );
                }).toList();
    }

    @Override
    public Set<EatenFoodResponse> getAll() {
        Set<EatenFoodResponse> eatenFoods = new HashSet<>();
        for (EatenFood eatenFood : eatenFoodRepository.findAll()) {
            Recipe recipe = null;
            if(eatenFood.getRecipeId() != null){
                recipe = recipeRepository.findById(eatenFood.getRecipeId())
                            .orElseThrow(() -> new RecipeNotFoundException(eatenFood.getRecipeId()));
            }
            eatenFoods.add(mapper.toResponse(eatenFood, recipe));
        }
        return eatenFoods;
    }

    @Override
    public void deleteById(UUID id) {
        eatenFoodRepository.deleteById(eatenFoodRepository.findById(id)
                .orElseThrow(() -> new EatenFoodNotFoundException(id)).getId());
    }

    @Override
    public void putById(UUID id, EatenFoodRequest eatenFoodRequest) {
        if (eatenFoodRepository.findById(id).isPresent()) {
            EatenFood eatenFood = mapper.toEntity(eatenFoodRequest);
            BaseUserDetails principal = (BaseUserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
            User user = userRepository.findById(principal.getId())
                    .orElseThrow(() -> new UserNotFoundException(principal.getId()));
            eatenFood.setUser(user);
            eatenFood.setId(id);
            UUID foodId = eatenFoodRequest.foodId();
            if (foodId != null) {
                eatenFood.setFood(foodRepository.findById(foodId)
                        .orElseThrow(() -> new FoodNotFoundException(foodId)));
            }
            eatenFoodRepository.save(eatenFood);
        } else {
            throw new EatenFoodNotFoundException(id);
        }
    }

}
