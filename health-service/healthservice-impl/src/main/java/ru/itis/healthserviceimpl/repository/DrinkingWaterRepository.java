package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.healthserviceimpl.model.DrinkingWater;

import java.util.UUID;

public interface DrinkingWaterRepository extends JpaRepository<DrinkingWater, UUID> {
}
