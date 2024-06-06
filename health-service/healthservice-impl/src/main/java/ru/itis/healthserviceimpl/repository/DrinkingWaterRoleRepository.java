package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.healthserviceimpl.model.role.DrinkingWaterRole;

import java.util.List;
import java.util.UUID;

public interface DrinkingWaterRoleRepository extends JpaRepository<DrinkingWaterRole, UUID> {
    List<DrinkingWaterRole> findByDrinkingWaterIdAndUserId(UUID drinkingWaterId, UUID userId);
}
