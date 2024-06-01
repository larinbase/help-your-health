package ru.itis.healthserviceapi.dto.response;



import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Модель для создание Food-response")
public record FoodResponse(@Schema(description = "Id") UUID id,
						   @Schema(description = "Дата создания") Instant createdDate,
						   @Schema(description = "Дата обновления") Instant updatedDate,
                           @Schema(description = "Название") String name,
                           @Schema(description = "Количество белков в 100 граммах") Short proteins,
                           @Schema(description = "Количество жиров в 100 граммах") Short fats,
                           @Schema(description = "Количество углеводов в 100 граммах") Short carbohydrates,
                           @Schema(description = "Количество калорий в 100 граммах") Short caloriesNumber,
                           @Schema(description = "Тип питания") Short typeOfFood,
                           @Schema(description = "Id категории") UUID categoryId) {
}
