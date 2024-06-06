package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.EatenFoodRequest;
import ru.itis.healthserviceapi.dto.response.EatenFoodResponse;
import ru.itis.healthserviceimpl.exception.EatenFoodNotFoundException;
import ru.itis.healthserviceimpl.exception.FoodNotFoundException;
import ru.itis.healthserviceimpl.mapper.EatenFoodMapper;
import ru.itis.healthserviceimpl.model.EatenFood;
import ru.itis.healthserviceimpl.repository.EatenFoodRepository;
import ru.itis.healthserviceimpl.repository.FoodRepository;
import ru.itis.healthserviceimpl.service.EatenFoodService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EatenFoodServiceImpl implements EatenFoodService {

    private final EatenFoodRepository eatenFoodRepository;

    private final FoodRepository foodRepository;

    private final EatenFoodMapper mapper;

    @Override
    @Cacheable(value = "eatenFood")
    public UUID save(EatenFoodRequest eatenFoodRequest) {
        try {
            EatenFood eatenFood = mapper.toEntity(eatenFoodRequest);
            UUID foodId = eatenFoodRequest.foodId();
            if (foodId != null) {
                eatenFood.setFood(foodRepository.findById(foodId)
                        .orElseThrow(() -> new FoodNotFoundException(foodId)));
            }
            return eatenFoodRepository.save(mapper.toEntity(eatenFoodRequest)).getId();
        } catch (FoodNotFoundException e) {
            throw new FoodNotFoundException(eatenFoodRequest.foodId());
        }
    }

    @Override
    @Cacheable(value = "eatenFood", key = "#id")
    public EatenFoodResponse getById(UUID id) {
        return mapper.toResponse(
                eatenFoodRepository.findById(id)
                        .orElseThrow(() -> new EatenFoodNotFoundException(id))
        );
    }

    @Override
    @Cacheable(value = "eatenFood")
    public Set<EatenFoodResponse> getAll() {
        Set<EatenFoodResponse> eatenFoods = new HashSet<>();
        for (EatenFood eatenFood : eatenFoodRepository.findAll()) {
            eatenFoods.add(mapper.toResponse(eatenFood));
        }
        return eatenFoods;
    }

    @Override
    @CacheEvict(value = "eatenFood", key = "#id")
    public void deleteById(UUID id) {
        eatenFoodRepository.deleteById(eatenFoodRepository.findById(id)
                .orElseThrow(() -> new EatenFoodNotFoundException(id)).getId());
    }

    @Override
    @CachePut(value = "eatenFood", key = "#id")
    public void putById(UUID id, EatenFoodRequest eatenFoodRequest) {
        if (eatenFoodRepository.findById(id).isPresent()) {
            EatenFood eatenFood = mapper.toEntity(eatenFoodRequest);
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
