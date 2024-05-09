package ru.itis.healthserviceimpl.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.healthserviceimpl.model.Recipe;

import java.util.List;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, ObjectId> {

    @Query("{title: ?0}")
    List<Recipe> findAllByTitleIgnoreCase(String title);

    @Query("{cookingTime: ?0}")
    List<Recipe> findAllByCookingTime(int cookingTime);

    List<Recipe> findAllByCategoriesContaining(String category);
}
