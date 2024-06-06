package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.UserSave;
import ru.itis.healthserviceapi.dto.request.UserUpdate;
import ru.itis.healthserviceapi.dto.response.UserResponse;
import ru.itis.healthserviceimpl.exception.CommunityRoleNotFoundException;
import ru.itis.healthserviceimpl.exception.UserAlreadyExistException;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.mapper.UserMapper;
import ru.itis.healthserviceimpl.model.role.CommunityRole;
import ru.itis.healthserviceimpl.model.additionalinfo.ActivityCoefficient;
import ru.itis.healthserviceimpl.model.additionalinfo.Nutrition;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roletype.CommunityRoleType;
import ru.itis.healthserviceimpl.repository.CommunityRoleRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;
import ru.itis.healthserviceimpl.service.UserService;
import ru.itis.healthserviceimpl.util.CalculateNutritionalInfoAndWaterNorm;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final CommunityRoleRepository communityRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Cacheable(value = "users", key = "#userSave.username")
    public UserResponse create(UserSave userSave) {
        log.info("check user in database by not existing");
        if (userRepository.findByUsername(userSave.username()).isPresent()) {
            throw new UserAlreadyExistException(userSave.username());
        }
        CommunityRoleType roleType = CommunityRoleType.USER;
        log.info("Find role id by type");
        CommunityRole role = communityRoleRepository.findByType(roleType)
                .orElseThrow(() -> new CommunityRoleNotFoundException(roleType.name()));
        log.info("mapping entity from dto");
        User user = mapper.fromRequest(userSave);
        user.setPassword(passwordEncoder.encode(userSave.password()));
        user.setRole(role);
        setNutritionalNorm(user);
        log.info("create user in database");
        return mapper.toResponse(userRepository.save(user));
    }

    @Override
    @Cacheable(value = "users", key = "#username")
    public UserResponse findByUsername(String username) {
        return mapper.toResponse(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException(username))
        );
    }

    @Override
    public UserResponse update(UserUpdate userUpdate) {
        BaseUserDetails principal = (BaseUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UserNotFoundException(principal.getUsername()));
        user.setFirstname(userUpdate.firstname());
        user.setLastname(userUpdate.lastname());
        user.setAge(userUpdate.age());
        user.setHeight(userUpdate.height());
        user.setWeight(user.getWeight());
        user.setActivityCoefficient(ActivityCoefficient.valueOf(userUpdate.activityCoefficient()));
        setNutritionalNorm(user);
        return mapper.toResponse(userRepository.save(user));
    }

    @Override
    @CacheEvict(value = "accounts", key = "#id")
    public void deleteById(UUID id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    @CacheEvict(value = "accounts", key = "#id")
    public void updateRole(UUID id, String roleType) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        CommunityRoleType communityRoleType = CommunityRoleType.valueOf(roleType);
        CommunityRole role = communityRoleRepository.findByType(communityRoleType)
                .orElseThrow(() -> new CommunityRoleNotFoundException(communityRoleType.name()));
        user.setRole(role);
        userRepository.save(user);
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
