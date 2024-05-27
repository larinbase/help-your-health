package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.healthserviceimpl.model.EatenFood;

import java.util.UUID;

@Repository
public interface EatenFoodRepository extends JpaRepository<EatenFood, UUID> {
}
