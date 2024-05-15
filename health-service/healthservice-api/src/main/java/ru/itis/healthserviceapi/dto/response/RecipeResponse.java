package ru.itis.healthserviceapi.dto.response;

import org.bson.types.ObjectId;

import java.util.List;

public record RecipeResponse(

        // TODO: автор - дто юзера
        ObjectId author,

        String title,

        List<String> categories,

        int cookingTime,

        List<IngredientResponse> ingredients,

        List<String> instructions,

        List<String> images,

        NutritionalInfoResponse nutritionalInfo
) {
}
