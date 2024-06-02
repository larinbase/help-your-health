package ru.itis.healthserviceimpl.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.itis.healthserviceapi.dto.request.RecipeRequest;
import ru.itis.healthserviceapi.dto.response.RecipeResponse;
import ru.itis.healthserviceimpl.model.Recipe;

import java.util.List;

@Mapper(componentModel = "spring", uses = {NutritionalInfoMapper.class, IngredientMapper.class})
public interface RecipeMapper {

    @Mapping(target = "id", ignore = true)
    Recipe toEntity(RecipeRequest request);

    RecipeResponse toResponse(Recipe recipe);

    default Page<RecipeResponse> toResponse(Page<Recipe> recipe) {
        return recipe.map(this::toResponse);
    }
}
