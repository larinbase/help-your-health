package ru.itis.healthserviceapi.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Модель для создания Eaten-Food-request")
public record EatenFoodRequest(@Schema(description = "Id продукта питания") UUID foodId,
                               @Schema(description = "Id рецепта") UUID recipeId,
                               @Schema(description = "Вес") Short weight) {
}
