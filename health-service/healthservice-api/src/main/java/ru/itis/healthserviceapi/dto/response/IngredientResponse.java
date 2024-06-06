package ru.itis.healthserviceapi.dto.response;

public record IngredientResponse(
        String name,

        float amount,

        String unit
) {
}
