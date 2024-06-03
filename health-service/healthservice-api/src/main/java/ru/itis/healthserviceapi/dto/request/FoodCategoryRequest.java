package ru.itis.healthserviceapi.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель для создания Food-Category-request")
public record FoodCategoryRequest(@Schema(description = "Название") String name) {
}
