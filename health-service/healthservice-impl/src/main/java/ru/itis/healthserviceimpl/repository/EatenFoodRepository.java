package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.healthserviceimpl.model.EatenFood;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface EatenFoodRepository extends JpaRepository<EatenFood, UUID> {

    List<EatenFood> findAllByUserIdAndCreateDateBetween(UUID userId, Instant from, Instant to);
}
