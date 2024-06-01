package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.healthserviceimpl.model.Food;

import java.util.UUID;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {

}
