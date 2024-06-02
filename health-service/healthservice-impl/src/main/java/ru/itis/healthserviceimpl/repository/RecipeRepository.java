package ru.itis.healthserviceimpl.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.healthserviceimpl.model.Recipe;

import java.util.UUID;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, UUID> {

    @Query(value = "{'title': {$regex : ?0, $options: 'i'}}")
    Page<Recipe> findAllByTitleRegex(String title, Pageable pageable);

    @Query("{cookingTime: ?0}")
    Page<Recipe> findAllByCookingTime(int cookingTime, Pageable pageable);

    Page<Recipe> findAllByCategoriesContaining(String category, Pageable pageable);
}
