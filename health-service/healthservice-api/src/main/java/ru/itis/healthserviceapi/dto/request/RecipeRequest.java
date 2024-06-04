package ru.itis.healthserviceapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record RecipeRequest(

        @NotBlank
        String title,

        @NotEmpty
        List<String> categories,

        @NotNull
        @Positive
        int cookingTime,

        @NotEmpty
        List<IngredientRequest> ingredients,

        @NotEmpty
        List<String> instructions,

        @NotEmpty
        List<String> images,

        @NotNull
        NutritionalInfoRequest nutritionalInfo

) {
}
