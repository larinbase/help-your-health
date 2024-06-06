package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.healthserviceimpl.model.EatenFoodRole;

import java.util.List;
import java.util.UUID;

public interface EatenFoodRoleRepository extends JpaRepository<EatenFoodRole, UUID> {
    List<EatenFoodRole> findByEatenFoodIdAndUserId(UUID EatenFoodRoleId, UUID userId);
}
