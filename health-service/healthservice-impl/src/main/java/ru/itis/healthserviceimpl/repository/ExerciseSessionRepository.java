package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.healthserviceimpl.model.ExerciseSessionEntity;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface ExerciseSessionRepository extends JpaRepository<ExerciseSessionEntity, UUID> {

    List<ExerciseSessionEntity> findAllByDate(Date date);
}