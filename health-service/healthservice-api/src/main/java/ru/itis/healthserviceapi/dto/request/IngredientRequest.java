package ru.itis.healthserviceapi.dto.request;

public record IngredientRequest(
        String name,

        float amount,

        String unit
) {
}
