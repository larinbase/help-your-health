package ru.itis.healthserviceapi.dto.response;

public record NutritionalInfoResponse(
        int calories,

        int protein,

        int fat,

        int carbohydrates
) {
}
