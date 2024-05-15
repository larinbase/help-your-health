package ru.itis.healthserviceapi.dto.request;

import java.util.List;

public record RecipeRequest(
        String title,

        List<String> categories,

        int cookingTime,

        List<IngredientRequest> ingredients,

        List<String> instructions,

        List<String> images,

        NutritionalInfoRequest nutritionalInfo

) {
}
