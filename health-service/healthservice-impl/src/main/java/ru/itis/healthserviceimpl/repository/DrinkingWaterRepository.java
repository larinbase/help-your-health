package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.healthserviceimpl.model.DrinkingWater;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DrinkingWaterRepository extends JpaRepository<DrinkingWater, UUID> {

    @Query("select e from DrinkingWater e where e.createDate = (select max(e.createDate) from DrinkingWater e where e.user.id = ?1)")
    Optional<DrinkingWater> findLastDrinkingWaterByUserId(UUID userId);

    List<DrinkingWater> findAllByUserId(UUID userId);

    List<DrinkingWater> findAllByUserIdAndCreateDateBetween(UUID userId, Instant from, Instant to);
}
