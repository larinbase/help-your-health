package ru.itis.healthserviceapi.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Модель для создание Food-request")
public record FoodRequest(@Schema(description = "Название") String name,
                          @Schema(description = "Количество белков в 100 граммах") Short proteins,
                          @Schema(description = "Количество жиров в 100 граммах") Short fats,
                          @Schema(description = "Количество углеводов в 100 граммах") Short carbohydrates,
                          @Schema(description = "Количество калорий в 100 граммах") Short caloriesNumber,
                          @Schema(description = "Тип питания") Short typeOfFood,
                          @Schema(description = "Id категории") UUID categoryId) {
}
