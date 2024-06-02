package ru.itis.healthserviceapi.dto.response;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.UUID;

public record RecipeResponse(

        UUID id,

        // TODO: автор - дто юзера
        UUID author,

        String title,

        List<String> categories,

        int cookingTime,

        List<IngredientResponse> ingredients,

        List<String> instructions,

        List<String> images,

        NutritionalInfoResponse nutritionalInfo
) {
}
