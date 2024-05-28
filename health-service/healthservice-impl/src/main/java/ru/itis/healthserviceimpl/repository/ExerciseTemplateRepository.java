package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.healthserviceimpl.model.ExerciseTemplateEntity;

import java.util.UUID;

public interface ExerciseTemplateRepository extends JpaRepository<ExerciseTemplateEntity, UUID> {
}
