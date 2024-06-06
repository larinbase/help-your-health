package ru.itis.healthserviceimpl.mapper;

import jakarta.annotation.Nullable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.healthserviceapi.dto.request.EatenFoodRequest;
import ru.itis.healthserviceapi.dto.response.EatenFoodResponse;
import ru.itis.healthserviceapi.dto.response.NutritionalInfoResponse;
import ru.itis.healthserviceimpl.model.EatenFood;
import ru.itis.healthserviceimpl.model.Food;
import ru.itis.healthserviceimpl.model.NutritionalInfo;
import ru.itis.healthserviceimpl.model.Recipe;

@Mapper(componentModel = "spring")
public interface EatenFoodMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "food", ignore = true)
    EatenFood toEntity(EatenFoodRequest eatenFoodRequest);

    @Mapping(source = "user.id", target = "userId")
    default EatenFoodResponse toResponse(EatenFood eatenFoodEntity, @Nullable Recipe recipe) {
        Food food = eatenFoodEntity.getFood();
        NutritionalInfo nutritionalInfo = null;
        if(food != null){
            nutritionalInfo = food.getNutrients();
        }else if (recipe != null){
            nutritionalInfo= recipe.getNutrients();
        }
        float weight100g = eatenFoodEntity.getWeight() / 100f;
        NutritionalInfoResponse nutrientsResponse = null;
        if(nutritionalInfo != null){
            nutrientsResponse = new NutritionalInfoResponse(
                    Math.round(nutritionalInfo.getCalories() * weight100g),
                    Math.round(nutritionalInfo.getProtein() * weight100g),
                    Math.round(nutritionalInfo.getFat() * weight100g),
                    Math.round(nutritionalInfo.getCarbohydrates() * weight100g)
            );
        }
        
        return new EatenFoodResponse(
                eatenFoodEntity.getId(),
                eatenFoodEntity.getCreateDate(),
                eatenFoodEntity.getLastUpdateDate(),
                eatenFoodEntity.getUser().getId(),
                food != null ? food.getId() : null,
                eatenFoodEntity.getRecipeId(),
                eatenFoodEntity.getWeight(),
                nutrientsResponse
        );
    }

}
