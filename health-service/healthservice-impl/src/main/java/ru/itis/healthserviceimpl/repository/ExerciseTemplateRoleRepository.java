package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.healthserviceimpl.model.ExerciseTemplateRole;

import java.util.List;
import java.util.UUID;

public interface ExerciseTemplateRoleRepository extends JpaRepository<ExerciseTemplateRole, UUID> {
    List<ExerciseTemplateRole> findByExerciseTemplateEntityIdAndUserId(UUID exerciseTemplateEntityId, UUID userId);
}
