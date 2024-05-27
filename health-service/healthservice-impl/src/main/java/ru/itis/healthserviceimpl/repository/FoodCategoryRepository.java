package ru.itis.healthserviceimpl.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.healthserviceimpl.model.FoodCategory;

import java.util.UUID;

@Repository
@Primary
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, UUID> {

}
