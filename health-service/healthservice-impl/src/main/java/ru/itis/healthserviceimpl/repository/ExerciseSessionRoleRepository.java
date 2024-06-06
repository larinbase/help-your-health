package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.healthserviceimpl.model.ExerciseSessionRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExerciseSessionRoleRepository extends JpaRepository<ExerciseSessionRole, UUID> {
    List<ExerciseSessionRole> findByExerciseSessionEntityIdAndUserId(UUID exerciseSessionEntityId, UUID userId);
}
