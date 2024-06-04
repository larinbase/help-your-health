package ru.itis.healthserviceapi.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

@Schema(description = "Модель для создание Food-request")
public record FoodRequest(

        @Schema(description = "Название")
        @NotBlank
        String name,

        @Schema(description = "Количество белков в 100 граммах")
        @NotNull
        @Positive
        Short proteins,

        @Schema(description = "Количество жиров в 100 граммах")
        @NotNull
        @Positive
        Short fats,

        @Schema(description = "Количество углеводов в 100 граммах")
        @NotNull
        @Positive
        Short carbohydrates,

        @Schema(description = "Количество калорий в 100 граммах")
        @NotNull
        @Positive
        Short caloriesNumber,

        @Schema(description = "Тип питания")
        @NotNull
        Short typeOfFood,

        @Schema(description = "Id категории")
        @NotNull
        UUID categoryId
) {
}
