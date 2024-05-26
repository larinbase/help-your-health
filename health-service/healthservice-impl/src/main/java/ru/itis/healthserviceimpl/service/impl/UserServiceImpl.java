package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.UserSave;
import ru.itis.healthserviceapi.dto.request.UserUpdate;
import ru.itis.healthserviceapi.dto.response.UserResponse;
import ru.itis.healthserviceimpl.mapper.UserMapper;
import ru.itis.healthserviceimpl.model.ActivityCoefficient;
import ru.itis.healthserviceimpl.model.Nutrition;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.service.UserService;
import ru.itis.healthserviceimpl.util.CalculateNutritionalInfoAndWaterNorm;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public void create(UserSave userSave) {
        if (repository.findByUsername(userSave.username()).isPresent()) {
            throw new IllegalArgumentException("User already exist"); // ToDo: Custom exception
        }
        User user = mapper.fromRequest(userSave);
        setNutritionalNorm(user);
        repository.save(user);
    }

    @Override
    public UserResponse findByUsername(String username) {
        return mapper.toResponse(
                repository.findByUsername(username)
                        .orElseThrow(() -> new IllegalArgumentException("User not found"))
        );
    }

    @Override
    public void update(UserUpdate userUpdate, UUID id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setFirstname(userUpdate.firstname());
        user.setLastname(userUpdate.lastname());
        user.setAge(userUpdate.age());
        user.setHeight(userUpdate.height());
        user.setWeight(user.getWeight());
        user.setActivityCoefficient(ActivityCoefficient.valueOf(userUpdate.activityCoefficient()));
        setNutritionalNorm(user);
        repository.save(user);
    }

    @Override
    public void deleteById(UUID id) {
        if (repository.findById(id).isEmpty()){
            throw new IllegalArgumentException("User not found");
        }
        repository.deleteById(id);
    }

    private void setNutritionalNorm(User user) {
        Map<Nutrition, Integer> nutritionalInfo = CalculateNutritionalInfoAndWaterNorm.calculateNutritionalInfo(
                user.getSex(), user.getWeight(), user.getHeight(), user.getAge(), user.getActivityCoefficient());
        user.setCalorieAllowance(nutritionalInfo.get(Nutrition.CALORIES));
        user.setProteins(nutritionalInfo.get(Nutrition.PROTEINS));
        user.setFats(nutritionalInfo.get(Nutrition.FATS));
        user.setCarbohydrates(nutritionalInfo.get(Nutrition.CARBOHYDRATES));
        user.setWaterNorm(nutritionalInfo.get(Nutrition.WATER_NORM));
    }
}
