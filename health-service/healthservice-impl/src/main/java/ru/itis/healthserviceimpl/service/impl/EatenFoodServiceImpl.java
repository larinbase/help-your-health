package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.EatenFoodRequest;
import ru.itis.healthserviceapi.dto.response.EatenFoodResponse;
import ru.itis.healthserviceimpl.exception.EatenFoodNotFoundException;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.mapper.EatenFoodMapper;
import ru.itis.healthserviceimpl.model.EatenFood;
import ru.itis.healthserviceimpl.repository.EatenFoodRepository;
import ru.itis.healthserviceimpl.service.EatenFoodService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EatenFoodServiceImpl implements EatenFoodService {

    private final EatenFoodRepository repository;

    private final EatenFoodMapper mapper;

    @Override
    public UUID save(EatenFoodRequest eatenFoodRequest) {
        try {
            return repository.save(mapper.toEntity(eatenFoodRequest)).getId();
        } catch (Exception e) {
            throw new UserNotFoundException(eatenFoodRequest.userId());
        }
    }

    @Override
    public EatenFoodResponse getById(UUID id) {
        return mapper.toResponse(
                repository.findById(id)
                        .orElseThrow(() -> new EatenFoodNotFoundException(id))
        );
    }

    @Override
    public Set<EatenFoodResponse> getAll() {
        Set<EatenFoodResponse> eatenFoods = new HashSet<>();
        for (EatenFood eatenFood : repository.findAll()) {
            eatenFoods.add(mapper.toResponse(eatenFood));
        }
        return eatenFoods;
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void putById(UUID id, EatenFoodRequest eatenFoodRequest) {
        if (repository.findById(id).isPresent()) {
            EatenFood eatenFood = mapper.toEntity(eatenFoodRequest);
            eatenFood.setId(id);
            repository.save(eatenFood);
        }
        else {
            throw new EatenFoodNotFoundException(id);
        }    }
}
