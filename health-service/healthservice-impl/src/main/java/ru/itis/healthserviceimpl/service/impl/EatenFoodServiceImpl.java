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
import ru.itis.healthserviceimpl.service.EatenFoodService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EatenFoodServiceImpl implements EatenFoodService {

    private final EatenFoodRepository eatenFoodRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final EatenFoodMapper mapper;
    private final EatenFoodRoleService eatenFoodRoleService;

    @Override
    public UUID save(EatenFoodRequest eatenFoodRequest) {
        try {
            EatenFood eatenFood = mapper.toEntity(eatenFoodRequest);
            UUID foodId = eatenFoodRequest.foodId();
            if (foodId != null) {
                eatenFood.setFood(foodRepository.findById(foodId)
                        .orElseThrow(() -> new FoodNotFoundException(foodId)));
            }
            BaseUserDetails principal = (BaseUserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
            User user = userRepository.findById(principal.getId())
                    .orElseThrow(() -> new UserNotFoundException(principal.getId()));
            EatenFood eatenFoodForSave = mapper.toEntity(eatenFoodRequest);
            eatenFoodForSave.setUser(user);
            EatenFood eatenFoodAfterSave = eatenFoodRepository.save(eatenFoodForSave);
            eatenFoodRoleService.create(user.getId(), eatenFoodAfterSave.getId());
            return eatenFoodAfterSave.getId();
        } catch (FoodNotFoundException e) {
            throw new FoodNotFoundException(eatenFoodRequest.foodId());
        }
    }

    @Override
    public EatenFoodResponse getById(UUID id) {
        return mapper.toResponse(
                eatenFoodRepository.findById(id)
                        .orElseThrow(() -> new EatenFoodNotFoundException(id))
        );
    }

    @Override
    public Set<EatenFoodResponse> getAll() {
        Set<EatenFoodResponse> eatenFoods = new HashSet<>();
        for (EatenFood eatenFood : eatenFoodRepository.findAll()) {
            eatenFoods.add(mapper.toResponse(eatenFood));
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
