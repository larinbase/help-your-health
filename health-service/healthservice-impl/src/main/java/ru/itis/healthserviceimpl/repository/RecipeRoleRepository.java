package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.healthserviceimpl.model.RecipeRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecipeRoleRepository extends JpaRepository<RecipeRole, UUID> {
    Optional<List<RecipeRole>> findByRecipeIdAndUserId(UUID recipeId, UUID userId);
}
