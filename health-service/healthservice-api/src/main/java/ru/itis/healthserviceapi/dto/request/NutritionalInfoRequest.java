package ru.itis.healthserviceapi.dto.request;

public record NutritionalInfoRequest(
        int calories,

        int protein,

        int fat,

        int carbohydrates
) {
}
