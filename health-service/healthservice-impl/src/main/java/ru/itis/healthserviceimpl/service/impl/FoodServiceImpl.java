package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.FoodRequest;
import ru.itis.healthserviceapi.dto.response.FoodResponse;
import ru.itis.healthserviceimpl.exception.FoodNotFoundException;
import ru.itis.healthserviceimpl.exception.ServiceException;
import ru.itis.healthserviceimpl.mapper.FoodMapper;
import ru.itis.healthserviceimpl.model.Food;
import ru.itis.healthserviceimpl.repository.FoodRepository;
import ru.itis.healthserviceimpl.service.FoodService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository repository;

    private final FoodMapper mapper;

    @Override
    public UUID save(FoodRequest foodRequest) {
        try {
            return repository.save(mapper.toEntity(foodRequest)).getId();
        } catch (Exception e) {
            throw new ServiceException("Bad food-create exception", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public FoodResponse getById(UUID id) {
        return mapper.toResponse(
                repository.findById(id)
                        .orElseThrow(() -> new FoodNotFoundException(id))
        );
    }

    @Override
    public Set<FoodResponse> getAll() {
        Set<FoodResponse> foods = new HashSet<>();
        for (Food food : repository.findAll()) {
            foods.add(mapper.toResponse(food));
        }
        return foods;
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void putById(UUID id, FoodRequest foodRequest) {
        if (repository.findById(id).isPresent()) {
            Food food = mapper.toEntity(foodRequest);
            food.setId(id);
            repository.save(food);
        }
        else {
            throw new FoodNotFoundException(id);
        }    }
}
