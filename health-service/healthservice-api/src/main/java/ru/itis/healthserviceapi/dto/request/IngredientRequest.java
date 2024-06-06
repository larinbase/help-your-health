package ru.itis.healthserviceapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IngredientRequest(

        @NotBlank
        String name,

        @NotNull
        @Positive
        float amount,

        @NotBlank
        String unit
) {
}
